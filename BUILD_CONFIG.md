# Build Configuration

## Test Configuration

### Running Tests Locally

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "ui.tests.LoginPageTest"

# Run with headed browser (not headless)
./gradlew test -Dheadless=false

# Run with verbose output
./gradlew test --info
```

## CI/CD Pipeline

The project uses GitHub Actions for continuous integration. Tests run automatically on:
- Push to `main` or `develop` branches
- Pull requests to `main` or `develop` branches

### Workflow Details

**File:** `.github/workflows/tests.yml`

**Steps:**
1. Checkout code
2. Set up JDK 21 (Temurin)
3. Grant execute permissions to Gradle wrapper
4. Build project (excluding tests)
5. Run all tests with headless Chrome
6. Upload test results as artifacts
7. Publish test report

### Artifacts

Test reports are uploaded and retained for 30 days:
- HTML test report: `app/build/reports/tests/test/`
- Test results XML: `app/build/test-results/test/`

## Local Environment

**Required:**
- Java 21+
- Git

**Optional:**
- Chrome/Chromium browser (WebDriverManager downloads ChromeDriver automatically)

## Gradle Tasks

```bash
# Build without running tests
./gradlew build -x test

# Clean build
./gradlew clean

# Run tests with output
./gradlew test --info

# Generate test report
./gradlew testReport
```

## Browser Configuration

Tests run in headless mode by default for CI/CD.

To run with a visible browser locally:
```bash
./gradlew test -Dheadless=false
```

The headless setting is configured in `app/src/test/kotlin/ui/core/BaseTest.kt`.
