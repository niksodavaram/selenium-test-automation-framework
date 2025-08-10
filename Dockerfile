# Stage 1: Build stage
FROM maven:3.8-openjdk-17 AS builder

WORKDIR /app

# Copy Maven wrapper files (Linux only)
COPY .mvn/ .mvn/
COPY mvnw ./

# Make Maven wrapper executable
RUN chmod +x mvnw

# Copy pom.xml and download dependencies
COPY pom.xml ./

# Copy source files and config files
COPY src ./src
COPY google_checks.xml ./
COPY testng.xml ./

# Download dependencies
RUN mvn dependency:resolve \
    dependency:resolve-plugins \
    dependency:go-offline

# Create directories for reports and screenshots
RUN mkdir -p ExtentReports Screenshots allure-results allure-reports

# Install Allure commandline
RUN apt-get update && apt-get install -y allure

# Run tests with reporting
RUN ./mvnw clean verify \
    checkstyle:check \
    -Dcheckstyle.config.location=google_checks.xml \
    surefire:test \
    allure:report \
    -Dsurefire.suiteXmlFiles=testng.xml

# Generate Allure HTML report
RUN allure generate allure-results --clean -o allure-reports

# Stage 2: Runtime stage
FROM openjdk:17-slim

# Install required dependencies
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip \
    chromium \
    chromium-driver \
    xvfb \
    allure \
    && rm -rf /var/lib/apt/lists/*

# Set Chrome and ChromeDriver versions
ENV CHROME_DRIVER_VERSION=latest
ENV DISPLAY=:99

# Create and use non-root user
RUN useradd -m -s /bin/bash selenium
USER selenium
WORKDIR /app

# Create directories in runtime container
RUN mkdir -p ExtentReports Screenshots allure-results allure-reports

# Copy test reports and artifacts
COPY --from=builder /app/target/surefire-reports ./test-reports
COPY --from=builder /app/allure-results ./allure-results
COPY --from=builder /app/allure-reports ./allure-reports
COPY --from=builder /app/ExtentReports ./ExtentReports
COPY --from=builder /app/Screenshots ./Screenshots
COPY --from=builder /app/target/*.jar app.jar

# Environment variables for reporting
ENV ALLURE_RESULTS_PATH=/app/allure-results
ENV ALLURE_REPORTS_PATH=/app/allure-reports
ENV EXTENT_REPORTS_PATH=/app/ExtentReports
ENV SCREENSHOTS_PATH=/app/Screenshots

# Set up virtual display
ENV DISPLAY=:99

# Expose port for report viewing
EXPOSE 9080
EXPOSE 9081

# Command to run tests
ENTRYPOINT ["xvfb-run", "java", "-jar", "app.jar"]

# Volume mounts for persistent reports
VOLUME ["/app/ExtentReports", "/app/Screenshots", "/app/allure-reports"]

# Script to generate and serve reports
CMD ["sh", "-c", "allure generate allure-results --clean -o allure-reports && allure serve allure-results"]