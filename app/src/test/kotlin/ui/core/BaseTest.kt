package ui.core

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

open class BaseTest {
    protected lateinit var driver: WebDriver

    @BeforeEach
    open fun setUp() {
        // Allow toggling headless via system property `-Dheadless=false`
        val headless = System.getProperty("headless", "true").toBoolean()

        WebDriverManager.chromedriver().setup()
        val options = ChromeOptions()
        if (headless) {
            // Use new headless mode flag for modern Chrome
            options.addArguments("--headless=new")
        }
        options.addArguments("--disable-gpu")
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-dev-shm-usage")
        options.addArguments("--window-size=1920,1080")

        driver = ChromeDriver(options)
    }

    @AfterEach
    open fun tearDown() {
        try {
            driver.quit()
        } catch (_: Exception) {
        }
    }
}
