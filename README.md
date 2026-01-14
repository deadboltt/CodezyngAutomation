# 🚀 Codezyng Automation Framework

**Selenium | TestNG | Maven | Allure | Extent | Jenkins**

---

## 📌 Overview

This repository contains a **robust, scalable UI automation framework** built using **Selenium WebDriver and TestNG**, designed to automate the **SauceDemo e-commerce application**.

The framework follows **industry best practices** and is suitable for:

* Real-world automation projects
* CI/CD pipelines (Jenkins)
* Automation testing interviews
* Learning modern Selenium architecture

---

## 🧰 Tech Stack

| Tool / Library           | Purpose                       |
| ------------------------ | ----------------------------- |
| **Java 17**              | Programming language          |
| **Selenium WebDriver 4** | UI automation                 |
| **TestNG 7.11**          | Test framework                |
| **Maven**                | Build & dependency management |
| **Allure**               | Advanced test reporting       |
| **Extent Reports**       | HTML execution reports        |
| **WebDriverManager**     | Driver binaries management    |
| **Apache POI**           | Excel data-driven testing     |
| **Log4j2**               | Logging                       |
| **Jenkins**              | CI/CD integration             |

---

## 🏗 Framework Design

* **Page Object Model (POM)**
* **ThreadLocal WebDriver management**
* **Config-driven execution**
* **Data-driven testing using Excel**
* **Headless execution support**
* **Retry logic for flaky tests**
* **Allure & Extent reporting**
* **CI-friendly Maven structure**

```
ecommerce-automation-framework
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.codezyng.automation
│   │   │       ├── base        # Core base classes
│   │   │       ├── config      # Configuration readers
│   │   │       ├── pages       # Page Object classes
│   │   │       └── utils       # Utility helpers
│   │   │
│   │   └── resources
│   │       └── log4j2.xml      # Logging configuration
│   │
│   └── test
│       ├── java
│       │   └── com.codezyng.automation
│       │       ├── base            # Test base setup
│       │       ├── dataproviders   # TestNG data providers
│       │       ├── listeners       # TestNG listeners (Extent / Retry)
│       │       ├── retry           # Retry logic
│       │       └── tests           # Test classes
│       │
│       └── resources
│            └── testdata            # Excel / test data files
│
├── pom.xml         # Maven configuration
└── README.md       # Project documentation


---

## ✅ Test Coverage

### 🔐 Login Tests

* Valid login
* Invalid login (Excel-driven)
* Empty credentials validation

### 🛍 Products Tests

* Products page load validation
* Add product to cart
* Cart badge verification

### 🛒 Cart Tests

* Add / remove product
* Cart validation

### 💳 Checkout Tests

* Complete checkout flow
* Order confirmation validation

---

## ⚙ Configuration (`config.properties`)

```properties
baseUrl=https://www.saucedemo.com/
browser=chrome
headless=false

implicitWait=10
explicitWait=20
pageLoadTimeout=30

testDataPath=src/test/resources/testdata/LoginData.xlsx
loginSheetName=Login

retryCount=1
```

### 🔄 CLI Overrides

```bash
mvn clean test -Dbrowser=edge -Dheadless=true
```

---

## ▶ Running Tests

### Run via Maven

```bash
mvn clean test
```

### Run via TestNG XML

```
Right-click testng.xml → Run as → TestNG Suite
```

---

## 🧪 Data-Driven Testing

* Excel-based test data using **Apache POI**
* Supports multiple rows and scenarios
* Used primarily for login validations

---

## 🔁 Retry Logic (TestNG 7 Compatible)

* Implemented using `IRetryAnalyzer`
* Applied globally via `IAnnotationTransformer`
* Configurable retry count
* Designed for **flaky UI failures**, not business logic failures

---

## 📊 Reporting

### ✅ Allure Reports

* Screenshots attached at runtime
* Supports PASS / FAIL / SKIP
* CI-friendly

```bash
mvn clean test
allure serve target/allure-results
```

### ✅ Extent Reports

* HTML report generated per execution
* Screenshots attached on failures
* Thread-safe implementation

```
target/extent-report/ExtentReport.html
```

---

## 🧵 Thread Safety & Stability

* WebDriver managed using **ThreadLocal**
* Safe setup & teardown lifecycle
* Prevents NullPointerExceptions
* Parallel-execution ready

---

## 🚀 Headless Execution

Enabled via config or CLI:

```properties
headless=true
```

or

```bash
mvn clean test -Dheadless=true
```

Ideal for:

* Jenkins
* CI pipelines
* Faster execution

---

## 🤖 Jenkins Integration

* Maven-based execution
* Compatible with Jenkins pipelines
* Allure & Extent reports can be published
* Follows Maven directory conventions

## 👤 Author

**Rajath Pai**
Automation Test Engineer

---

## ⭐ Key Highlights (Interview-Ready)

* Clean BaseTest lifecycle
* ThreadLocal WebDriver design
* Retry logic (TestNG 7 safe)
* Allure + Extent dual reporting
* Headless & CI support
* Production-grade Maven structure

---

## 📌 Future Enhancements

* Parallel execution via TestNG
* Selenium Grid / Docker support
* GitHub Actions CI
* API automation integration
* Visual regression testing
