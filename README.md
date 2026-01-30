# Playwright + Cucumber Automation Framework (Java)

This project is an end-to-end UI automation framework built with:

- Java 17
- Playwright for browser automation
- Cucumber (BDD)
- JUnit Platform
- GitHub Actions CI

It uses Wikipedia as a stable, public website for demonstration purposes.

---

## Tech Stack

- **Java 17**
- **Playwright (Java)**
- **Cucumber JVM**
- **Maven**
- **GitHub Actions**

---

## Project Structure
```
src/test
├── java
│ ├── base # Hooks, TestContext, Playwright lifecycle
│ ├── pages # Page Objects (locators + behaviour)
│ ├── steps # Cucumber step definitions
│ └── runners # Cucumber test runner
│
└── resources
└── features # Gherkin feature files
```
---

## Key Design Principles

- One browser context per scenario
- No static Playwright state
- Centralised locators inside page objects
- Thin step definitions (behaviour only)
- Playwright auto-waiting (no manual sleeps)

---

## Running Tests Locally

Headed mode (default):
```bash
mvn test -DHeadless=true
```

---

## CI Pipeline

- Runs on every push and pull request to ```main```
- Executes tests headlessly on Ubuntu
- Uploads reports, screenshots and Playwright traces as artefacts
- Playwright traces are captured **only on failure**

---

## Reports & Debugging

- Cucumber HTML report: ```target/cucumber-report.html```
- Failure screenshots: attached to Cucumber scenarios
- Playwright traces (on failure): ```target/traces/*.zip```

- Traces can be viewed locally with:
```bash
npx playwright show-trace trace.zip
```

---

## Notes

This project focuses on framework structure, maintainability, 
and CI stability rather than exhaustive test coverage.