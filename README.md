# Kotlin UI & API Test Project

[![Run Tests](https://github.com/gastonm05/KotlinUiApiTestProject/actions/workflows/tests.yml/badge.svg)](https://github.com/gastonm05/KotlinUiApiTestProject/actions/workflows/tests.yml)

A Kotlin-based test automation project showcasing Selenium WebDriver with Page Object Model (POM) pattern and API testing with REST Assured. Features automated CI/CD pipeline with GitHub Actions.

## Project Structure

```
app/src/test/kotlin/
├── ui/
│   ├── core/
│   │   └── BaseTest.kt              # Shared WebDriver setup/teardown
│   ├── pages/
│   │   ├── HomePage.kt              # Home page object with 45+ link locators
│   │   ├── LoginPage.kt             # Login page object
│   │   └── SecurePage.kt            # Secure area page object
│   └── tests/
│       ├── HomePageTest.kt          # Home page tests
│       └── LoginPageTest.kt         # Login tests (valid + invalid scenarios)
└── api/
    ├── clients/                     # REST clients (User, Post)
    ├── models/                      # Data classes (User, Post + nested)
    ├── testdata/                    # Factories for reusable test data
    ├── tests/                       # API test suites (User, Post)
    └── utils/                       # ApiConfig, RestAssuredConfig, validators
```

## Technologies

- **Language:** Kotlin
- **Test Framework:** JUnit Jupiter (JUnit 5)
- **Browser Automation:** Selenium WebDriver 4.39.0
- **Driver Management:** WebDriverManager 5.6.3
- **API Testing:** REST Assured 5.3.0
- **Build Tool:** Gradle 8.7
- **Java:** JDK 21 (Eclipse Adoptium)
- **CI/CD:** GitHub Actions

## CI/CD Pipeline

This project includes automated continuous integration that runs on every push and pull request to `main` or `develop` branches.

### Features
- ✅ Automated test execution in headless Chrome
- ✅ Test results published as check runs
- ✅ HTML test reports uploaded as artifacts (retained for 30 days)
- ✅ Build status badge on README
- ✅ Parallel test execution

### Viewing Test Results
- **GitHub Actions:** [View workflow runs](https://github.com/gastonm05/KotlinUiApiTestProject/actions)
- **Test Reports:** Download from workflow run artifacts
- **Status Badge:** Shows current build status at the top of this README

For detailed CI/CD configuration, see [BUILD_CONFIG.md](BUILD_CONFIG.md).

## Running Tests

### Build and Run All Tests
```bash
./gradlew test
```

### Run Specific Test Class
```bash
./gradlew test --tests "ui.tests.LoginPageTest"
```

### Run All API Tests
```bash
./gradlew test --tests "api.tests.*"
```

### Run API Suites Individually
```bash
./gradlew test --tests "api.tests.UserApiTests"
./gradlew test --tests "api.tests.PostApiTests"
```

### Run Tests with Headed Browser
```bash
./gradlew test -Dheadless=false
```

### Run Tests in Watch Mode
```bash
./gradlew test --continuous
```

## Features

### Page Object Model (POM)
- Centralized locators and interactions for each page
- Reusable methods for common operations
- Clear separation of test logic from page logic

### WebDriver Management
- Automatic ChromeDriver binary download via WebDriverManager
- Centralized driver initialization and teardown in `BaseTest`
- Headless mode by default (configurable via JVM property)

### Explicit Waits
- WebDriverWait with customizable timeouts
- ExpectedConditions for robust element interactions
- Flash message handling with text normalization

### API Testing
- REST Assured with a shared request specification and base URL
- Jackson Kotlin module for data-class deserialization
- Reusable API clients (User, Post) with CRUD helpers
- TestData factory object for consistent fixtures
- ResponseValidator helpers for status/content-type/body/time checks

### Test Scenarios

#### Login Tests
- ✅ Valid login with username & password
- ✅ Invalid username scenario
- ✅ Invalid password scenario
- ✅ Invalid credentials (both wrong)
- ✅ Navigation verification to Secure page

#### Home Page Tests
- ✅ Header visibility verification
- ✅ Multiple link locators (AB Testing, Form Auth, etc.)

## Configuration

### Driver Options
Configured in [app/src/test/kotlin/ui/core/BaseTest.kt](app/src/test/kotlin/ui/core/BaseTest.kt):
- **Headless mode:** `--headless=new` (default enabled for CI, override with `-Dheadless=false`)
- **GPU disabled:** `--disable-gpu`
- **Sandbox disabled:** `--no-sandbox`
- **Shared memory:** `--disable-dev-shm-usage` (prevents crashes in containerized environments)
- **Window size:** `1920x1080`

### API Config
- Base URL and endpoints: [app/src/test/kotlin/api/utils/ApiConfig.kt](app/src/test/kotlin/api/utils/ApiConfig.kt)
- REST Assured setup with Jackson Kotlin: [app/src/test/kotlin/api/utils/RestAssuredConfig.kt](app/src/test/kotlin/api/utils/RestAssuredConfig.kt)
- Shared validation helpers: [app/src/test/kotlin/api/utils/ResponseValidator.kt](app/src/test/kotlin/api/utils/ResponseValidator.kt)
- Test data factories: [app/src/test/kotlin/api/testdata/TestData.kt](app/src/test/kotlin/api/testdata/TestData.kt)

## Development

### Creating New Page Objects
1. Create a new class in `ui/pages/`
2. Define locators as companion object constants using `By.*`
3. Implement page-specific methods
4. Inherit from `BaseTest` for test classes

### Example
```kotlin
// app/src/test/kotlin/ui/pages/ExamplePage.kt
package ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class ExamplePage(val driver: WebDriver) {
    companion object {
        val BASE_URL = "https://example.com"
        val HEADING = By.tagName("h1")
        val BUTTON = By.id("submit")
    }

    fun clickButton() {
        driver.findElement(BUTTON).click()
    }
}
```

## Test Results

### Local
Test reports are generated in `app/build/reports/tests/test/index.html` after each test run.

### CI/CD
- Test results are published as GitHub Check Runs
- HTML reports are uploaded as workflow artifacts
- Test summary appears in the Actions tab for each workflow run

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

All PRs will automatically trigger the test suite via GitHub Actions.

## License

This project is open source and available under the MIT License.

## Author

Gaston Mugas (gaston.mugas@gmail.com)
