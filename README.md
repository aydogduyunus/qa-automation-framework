# qa-automation-framework
# QA Automation Framework

This project is an end-to-end QA automation framework that covers UI testing, database verification, API integration, and CI/CD processes. It uses the demo form at https://demoqa.com/text-box.

---

## 🔧 Technologies Used

- Java 17
- Selenium WebDriver
- TestNG
- Docker & Selenium Grid
- PostgreSQL (pgAdmin)
- Maven
- Allure Report
- GitHub Actions (CI/CD)

---

## 📁 Project Structure

```
ui-tests
├── .github/workflows         # GitHub Actions CI/CD
├── allure-results            # Allure results and environment.json
├── src/test/java
│   └── com.qaautomation
│       ├── base              # Base test setup
│       ├── pages             # Page Object Model classes
│       ├── tests             # Test classes
│       └── utils             # Utilities (DB, screenshot)
├── testng.xml                # TestNG configuration
└── pom.xml                   # Maven dependencies
```

---

## 🚀 How to Run the Project

### 1. Start Selenium Grid with Docker
```
docker-compose up -d
```

### 2. Run the tests
```
mvn clean test
```

### 3. View Allure Report
```
allure serve allure-results
```

---

## ✅ What’s Included

- Form filling and submission for DemoQA TextBox page
- Screenshot capture on failure
- Database recording (PostgreSQL)
- CI/CD with GitHub Actions
- Allure report with environment info

---

## 🔍 Allure Report: environment.json Sample
```json
{
  "Browser": "Chrome",
  "Java Version": "17",
  "Platform": "GitHub Actions - Ubuntu",
  "Test Framework": "TestNG"
}
```

---

## 🙋 Author

Created by [Yunus Aydogdu](https://github.com/aydogduyunus)  
Software Test Engineer | Passionate about UI, API, and backend test automation

---

## 📌 Notes

- SQL verification is optional and can be activated later.
- API tests (POST/GET) are planned as the next feature.
- Custom log steps for Allure can be added optionally.

