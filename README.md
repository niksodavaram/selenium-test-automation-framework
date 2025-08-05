<p align="center">
  <!-- Replace with your logo if available -->
  <img src="https://cdn.simpleicons.org/selenium/43B02A" alt="Selenium" width="60" alt="Project logo" width="180" />
</p>

<h1 align="center">
  Selenium Test Automation Framework
</h1>

<p align="center">
  <b>Modern, scalable test automation framework built with Java, Selenium, TestNG, and Singleton design pattern.</b>
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

This project is a **robust, maintainable, and scalable automation testing framework** for web applications, built using:

- 🟦 **Java**
- 🟦 **Selenium WebDriver**
- 🟦 **TestNG**
- 🟦 **Singleton Pattern**
- 🟦 **Maven**
- 🟦 **Docker** (optional)
- 🟦 **GitHub Actions** for CI/CD

It is designed to help teams quickly automate UI test cases as per requirements, with support for running tests locally or in CI pipelines.

---

## 📂 Project Structure
```commandline
Singletgon-based-Java-Selenium-Testing-Framework/
├── .github/workflows/ # GitHub Actions CI workflows
├── .idea/ # IntelliJ IDEA settings (should be gitignored)
├── .mvn/wrapper/ # Maven Wrapper
├── docker/ # Docker support files
├── Screenshots/ # Test evidence/screenshots
├── src/
│ ├── main/
│ └── test/
├── Dockerfile
├── docker-compose-v3.yml
├── pom.xml
├── testng.xml
└── README.md
```
---

## 🛠️ Getting Started

### **Prerequisites**

- Java 17+
- Maven 3.6+
- (Optional) Docker

### **Clone the repo:**

```bash
git clone https://github.com/niksodavaram/Singletgon-based-Java-Selenium-Testing-Framework.git
cd Singletgon-based-Java-Selenium-Testing-Framework
```
Install dependencies:
```bash
mvn clean install
```
Run tests:
bash

mvn test

or with TestNG:
bash

mvn clean test -DsuiteXmlFile=testng.xml

Run with Docker:

    (If supported in your project)

bash

docker-compose up --build

⚙️ Features

    Singleton WebDriver Management for efficient browser resource usage
    Page Object Model (POM) for maintainable test code
    TestNG for powerful test orchestration
    CI/CD integration via GitHub Actions
    Environment configuration via YAML/properties
    Extensible for multiple browsers and environments
    Screenshot capture for failed tests
    Allure reporting and more

📸 Screenshots

<p align="center">

<img src="Screenshots/test_report.png" alt="Sample Test Report" width="600" />

</p>
🔗 Useful Commands
Command	Description
mvn clean install	Build and verify tests
mvn test	Run all tests
docker-compose up --build	Run tests in Docker
📑 Documentation

    See testng.xml for suite and test group configuration.
    For test case details, refer to the provided Excel artifacts (RTM/test cases).
    See .github/workflows/ for CI/CD setup.

🤝 Contributing

Contributions are welcome!

    Fork this repo
    Create your feature branch (git checkout -b feature/YourFeature)
    Commit your changes (git commit -am 'Add new feature')
    Push to the branch (git push origin feature/YourFeature)
    Open a Pull Request

📄 License

This project is licensed under the MIT License.
Feel free to use, share, and improve!
🙋‍♂️ Contact

For questions, open an issue or contact the maintainer.

<p align="center">

⭐️ If you like this project, give it a star! ⭐️

</p>

```
Notes:

    Logo:
        If you have a logo, place it in Screenshots/logo.png or update the path accordingly.
        If not, you can comment/remove the logo <img> tag.

    GitHub Actions Badge:
        Update your-workflow.yml in the badge URL to match your actual workflow file name (e.g., ci.yml, main.yml).

    License:
        If you have a LICENSE file, the badge will work. If not, you may remove or update that badge.

    Screenshots:
        Add real screenshot files as needed.
