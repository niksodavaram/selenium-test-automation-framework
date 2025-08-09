<p align="center">
  <img src="https://cdn.simpleicons.org/selenium/43B02A" alt="Selenium" width="60" />
</p>

<h1 align="center">
  Selenium Test Automation Framework
</h1>

<p align="center">
  <b>Modern, scalable test automation framework built with Java, Selenium, TestNG, Singleton, and best practices.</b>
</p>

<p align="center">
  <a href="https://github.com/niksodavaram/Singletgon-based-Java-Selenium-Testing-Framework/actions">
    <img src="https://img.shields.io/github/actions/workflow/status/niksodavaram/Singletgon-based-Java-Selenium-Testing-Framework/your-workflow.yml?branch=master&logo=github&label=CI%20Build" alt="CI Build Status" />
  </a>
  <a href="https://github.com/niksodavaram/Singletgon-based-Java-Selenium-Testing-Framework/stargazers">
    <img src="https://img.shields.io/github/stars/niksodavaram/Singletgon-based-Java-Selenium-Testing-Framework?style=social" alt="GitHub stars" />
  </a>
  <a href="https://github.com/niksodavaram/Singletgon-based-Java-Selenium-Testing-Framework/network/members">
    <img src="https://img.shields.io/github/forks/niksodavaram/Singletgon-based-Java-Selenium-Testing-Framework?style=social" alt="GitHub forks" />
  </a>
  <img src="https://img.shields.io/badge/Java-17%2B-blue.svg" alt="Java Version" />
  <img src="https://img.shields.io/badge/Selenium-4.x-brightgreen" alt="Selenium Version" />
  <img src="https://img.shields.io/badge/TestNG-7.x-orange" alt="TestNG Version" />
  <img src="https://img.shields.io/github/license/niksodavaram/Singletgon-based-Java-Selenium-Testing-Framework" alt="License" />
</p>

---

## 🚀 Overview

A robust, maintainable, and **scalable QA automation framework** for web applications, built using:

- 🟦 **Java 17+**
- 🟦 **Selenium WebDriver 4.x**
- 🟧 **TestNG 7.x** (parallel execution, factories, dataproviders)
- 🟦 **Singleton Pattern** (with `ThreadLocal` for parallel-safe WebDriver)
- 🟦 **Extent Reports** (custom `ExtentFactory`)
- 🟦 **Maven** (Surefire plugin fork mode)
- 🟦 **JSON & Excel data sources**
- 🟦 **Custom Listeners** (TestNG, WebDriver)
- 🟦 **Docker** (optional)
- 🟦 **GitHub Actions** for CI

---

## 📂 Project Structure
```
Selenium-Test-Automation-Framework/
├── .github/workflows/ # CI workflows
├── Screenshots/ # Test evidence/screenshots
├── src/
│ ├── main/
│ └── test/
│ ├── java/
│ │ ├── base/
│ │ ├── dto/
│ │ ├── factories/
│ │ ├── listeners/
│ │ │ ├── TestNGListeners.java
│ │ │ └── WebDriverListener.java
│ │ ├── pages/
│ │ ├── reportgenerator/
│ │ │ └── ExtentFactory.java
│ │ ├── tests/
│ │ └── utils/
│ └── resources/
│ ├── testData/
│ │ └── successMessageTestData.json
│ └── testcases.xlsx
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── testng.xml
└── README.md
``````


---

## 🏗️ Key Features & Design Patterns

- **Singleton ThreadLocal WebDriver:**  
  Ensures thread-safe browser sessions for parallel tests.

- **Page Object Model (POM):**  
  Modular, maintainable page abstractions.

- **Parallel Execution:**  
  Runs tests in parallel via TestNG and Maven Surefire (`forkCount > 1`).

- **Hybrid Data Sources:**
    - **Excel DataProvider:** For parameterized tests using Excel files.
    - **JSON TestNG Factory:** For dynamic test instance creation using JSON test data and TestNG’s `@Factory`.

- **Advanced Reporting:**
    - **ExtentFactory:** Centralized, singleton-based ExtentReports management.
    - **Screenshot Attachments:** For failed steps.

- **Custom Listeners:**
    - **TestNGListeners:** Implements `ITestListener` for reporting, logging, and hooks.
    - **WebDriverListener:** Implements WebDriver event hooks for enhanced logging, screenshot, and debugging.

- **CI/CD Ready:**  
  Integrated with GitHub Actions.

---

## 💡 Recent Enhancements

- **Integrated `ExtentFactory`**:  
  Singleton ExtentReports instance for consistent, thread-safe reporting.

- **Custom `TestNGListeners` and `WebDriverListener`**:  
  Hooks for test start, pass, fail, and browser events (e.g., automatic screenshot capture on failure).

- **TestNG Factory Pattern with JSON**:  
  Generates multiple test instances dynamically from JSON data, enabling true data-driven and parallelized testing.

- **Hybrid Data Providers**:  
  Use both Excel-based DataProvider and JSON-factory-based approaches in the same suite.

- **Robust Parallelism**:  
  ThreadLocal WebDriver + TestNG parallel mode + Maven Surefire forked JVMs = safe, scalable parallel test execution.

---

## 🧑‍💻 Example: TestNG Factory with JSON

```java
@Factory
public Object[] createInstances() {
    List<SuccessMessageDTO> testData = JsonTestDataReader.getSuccessMessageData("testData/successMessageTestData.json");
    if (testData == null || testData.isEmpty()) throw new RuntimeException("No test data found!");
    return testData.stream().map(ValidatingSuccessMessageTests::new).toArray();
}
```
🧑‍💻 Example: Excel DataProvider
```java

@DataProvider(name = "excelData")
public Object[][] getData() {
    return ExcelUtils.readExcelData("testcases.xlsx", "Sheet1");
}
```

## 🏃 Running Tests

Local:
```bash
mvn clean install
mvn test
````
Specific TestNG Suite:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
````
Parallel Execution:
Configurable via testng.xml and Maven Surefire plugin in pom.xml
Example in testng.xml:
```xml
<suite name="Parallel Suite" parallel="methods" thread-count="5">
        ...
</suite>
```
Docker:
```bash
docker-compose up --build
````
## 📑 Reporting
```
Extent Reports:
HTML reports (with screenshots) are auto-generated after each run.
Allure Reporting:
(If enabled in your project)
```
## 🧩 Extensibility

* Add new browsers by extending WebDriverManager
* Add new data sources (CSV, DB, etc.)
* Add more listeners for advanced hooks

## 🤝 Contributing

    Fork this repo
    Create your feature branch (git checkout -b feature/YourFeature)
    Commit your changes (git commit -am 'Add new feature')
    Push to the branch (git push origin feature/YourFeature)
    Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

<p align="center">

⭐️ If you like this project, give it a star! ⭐️

</p>

```