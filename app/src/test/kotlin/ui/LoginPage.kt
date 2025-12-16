package ui

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class LoginPage(private val driver: WebDriver) {

    companion object {
        private const val BASE_URL = "https://the-internet.herokuapp.com/login"
        private val HEADING = By.tagName("h2")
        private val USERNAME_INPUT = By.id("username")
        private val PASSWORD_INPUT = By.id("password")
        private val LOGIN_BUTTON = By.cssSelector("button[type='submit']")
        private val FLASH_MESSAGE = By.id("flash")
        private val FLASH_MESSAGE_TEXT = By.id("flash")
    }

    fun navigateTo() {
        driver.get(BASE_URL)
    }

    fun getPageHeading(): String {
        return driver.findElement(HEADING).text
    }

    fun enterUsername(username: String) {
        driver.findElement(USERNAME_INPUT).sendKeys(username)
    }

    fun enterPassword(password: String) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password)
    }

    fun clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click()
    }

    fun login(username: String, password: String) {
        enterUsername(username)
        enterPassword(password)
        clickLoginButton()
    }

    fun isFlashMessageDisplayed(): Boolean {
        return try {
            val elem = WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(FLASH_MESSAGE))
            elem?.isDisplayed ?: false
        } catch (e: Exception) {
            false
        }
    }

    fun getFlashMessage(): String {
        return try {
            val text = WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(FLASH_MESSAGE_TEXT))?.text ?: ""
            text.replace("\n", " ").replace("×", "").trim()
        } catch (e: Exception) {
            ""
        }
    }

    fun isLoaded(): Boolean {
        return try {
            driver.findElement(HEADING).isDisplayed
        } catch (e: Exception) {
            false
        }
    }
}
