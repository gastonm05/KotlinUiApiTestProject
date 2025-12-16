package ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class SecurePage(private val driver: WebDriver) {

    companion object {
        private const val BASE_URL = "https://the-internet.herokuapp.com/secure"
        private val HEADING = By.tagName("h2")
        private val FLASH = By.id("flash")
        private val LOGOUT_LINK = By.cssSelector("a[href='/logout']")
    }

    fun isLoaded(): Boolean {
        return try {
            val elem = WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(HEADING))
            elem?.isDisplayed ?: false
        } catch (e: Exception) {
            false
        }
    }

    fun getHeadingText(): String {
        return try {
            driver.findElement(HEADING).text
        } catch (e: Exception) {
            ""
        }
    }

    fun getFlashMessage(): String {
        return try {
            val text = WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(FLASH))?.text ?: ""
            text.replace("\n", " ").replace("×", "").trim()
        } catch (e: Exception) {
            ""
        }
    }

    fun clickLogout() {
        try {
            driver.findElement(LOGOUT_LINK).click()
        } catch (_: Exception) {
        }
    }
}
