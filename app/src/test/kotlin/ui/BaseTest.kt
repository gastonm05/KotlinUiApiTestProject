package ui

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

open class BaseTest {
    protected lateinit var driver: WebDriver

    @BeforeEach
    open fun setUp() {
        driver = ChromeDriver()
    }

    @AfterEach
    open fun tearDown() {
        try {
            driver.quit()
        } catch (_: Exception) {
        }
    }
}
