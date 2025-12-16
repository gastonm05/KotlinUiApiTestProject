# Kotlin UI & API Test Project

A Kotlin-based test automation project showcasing Selenium WebDriver with Page Object Model (POM) pattern and API testing with REST Assured.

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
    └── UserApiTest.kt              # API tests with REST Assured
```

## Technologies

- **Language:** Kotlin
- **Test Framework:** JUnit Jupiter (JUnit 5)
- **Browser Automation:** Selenium WebDriver 4.39.0
- **Driver Management:** WebDriverManager 5.6.3
- **API Testing:** REST Assured 5.3.0
- **Build Tool:** Gradle 8.7
- **Java:** JDK 21 (Eclipse Adoptium)

## Running Tests

### Build and Run All Tests
```bash
./gradlew test
```

### Run Specific Test Class
```bash
./gradlew test --tests "ui.tests.LoginPageTest"
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
- **Headless mode:** `--headless=new` (default on, override with `-Dheadless=false`)
- **GPU disabled:** `--disable-gpu`
- **Sandbox disabled:** `--no-sandbox`
- **Window size:** `1920x1080`

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

Test reports are generated in `app/build/reports/tests/test/index.html` after each test run.

## License

This project is open source and available under the MIT License.

## Author

Gaston Mugas (gaston.mugas@gmail.com)
