\# Ecommerce Automation Framework

Selenium · TestNG · Maven · Jenkins · Allure

**1. Project Overview**

This project is an end-to-end UI automation framework built using
Selenium WebDriver and TestNG, designed to automate an e-commerce web
application (SauceDemo).

The framework supports:

\* Page Object Model (POM)

\* Data-driven testing

\* Parallel execution

\* Retry logic

\* CI/CD integration with Jenkins

\* Rich reporting using Allure

\* Email notifications after build completion

**2. Tech Stack**

\| Tool \| Purpose \|

\| \-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-- \|
\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-\-- \|

\| Java 17 \| Programming language \|

\| Selenium WebDriver \| UI automation \|

\| TestNG \| Test framework \|

\| Maven \| Build & dependency management \|

\| Jenkins \| CI/CD \|

\| Allure \| Test reporting \|

\| WebDriverManager \| Browser driver management \|

\| Log4j2 \| Logging \|

**3. Test Coverage**

Login Tests

\* Valid login

\* Invalid login (data-driven)

Product Tests

\* Products page load verification

\* Add product to cart

\* Validate cart badge count

Cart Tests

\* Add product to cart

\* Remove product from cart

\* Validate cart is empty

Checkout Tests

\* Complete checkout flow

\* Validate checkout confirmation

**4. Configuration**

config.properties

baseUrl=https://www.saucedemo.com

browser=chrome

headless=false

timeout=10

**5. Running Tests Locally**

Prerequisites

\* Java 17 installed

\* Maven installed

\* Chrome browser

**Command**

bash

mvn clean test

**6. Jenkins CI/CD Integration**

Jenkins Features

\* Pipeline as Code (\`Jenkinsfile\`)

\* Allure Report published on left sidebar

\* Email notifications after build completion

\* Supports scheduled (overnight) execution

Jenkins Job Type

Pipeline

SCM: GitHub repository

7\. Allure Reporting

Configuration

src/test/resources/allure.properties

allure.results.directory=target/allure-results

Access Report

From Jenkins:

Job → Build → Allure Report

**9. Email Notifications**

Email sent after each build

Includes:

1.  Build status

2.  Jenkins build link

3.  Allure report link

**9. Retry & Stability**

\* Retry logic applied only for \*\*TimeoutException\*\*

\* Supports parallel execution

\* Thread-safe WebDriver handling

**10. Git & Clean Repo Practices**

\* Build artifacts excluded via \`.gitignore\`

\* No \`target/\` or report files committed

\* Jenkins & CI friendly

11\. Author

Rajath Pai

Automation Test Engineer
"# SauceDemoAutomation" 
"# SauceDemoAutomation" 
