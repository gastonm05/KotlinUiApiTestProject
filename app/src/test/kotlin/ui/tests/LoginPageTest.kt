package ui.tests

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import ui.core.BaseTest
import ui.pages.HomePage
import ui.pages.LoginPage
import ui.pages.SecurePage

class LoginPageTest : BaseTest() {

    private lateinit var loginPage: LoginPage
    private lateinit var securePage: SecurePage
    private lateinit var homePage: HomePage

    @BeforeEach
    override fun setUp() {
        super.setUp()
        homePage = HomePage(driver)
        loginPage = LoginPage(driver)
        securePage = SecurePage(driver)
    }

    @Test
    fun testValidLogin() {
        // Navigate to HomePage and click on 'Form Authentication' link
        homePage.navigateTo()
        homePage.clickLinkByText("Form Authentication")

        // Verify we're on the Login page
        assertTrue(loginPage.isLoaded(), "Login page should be loaded")

        // Perform login with valid credentials
        loginPage.login("tomsmith", "SuperSecretPassword!")

        // Verify navigation to Secure page and success flash
        assertTrue(securePage.isLoaded(), "Secure page should be loaded after login")
        assertTrue(securePage.getFlashMessage().contains("You logged into a secure area!"),
            "Flash message should contain success message")
    }

    @Test
    fun testInvalidUsername() {
        // Navigate to HomePage and click on 'Form Authentication' link
        homePage.navigateTo()
        homePage.clickLinkByText("Form Authentication")

        // Verify we're on the Login page
        assertTrue(loginPage.isLoaded(), "Login page should be loaded")

        // Perform login with invalid username
        loginPage.login("invaliduser", "SuperSecretPassword!")

        // Verify failed login
        assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message should be displayed")
        assertTrue(
            loginPage.getFlashMessage().contains("invalid"),
            "Flash message should contain invalid credentials message"
        )
    }

    @Test
    fun testInvalidPassword() {
        // Navigate to HomePage and click on 'Form Authentication' link
        homePage.navigateTo()
        homePage.clickLinkByText("Form Authentication")

        // Verify we're on the Login page
        assertTrue(loginPage.isLoaded(), "Login page should be loaded")

        // Perform login with correct username but invalid password
        loginPage.login("tomsmith", "InvalidPassword123")

        // Verify failed login
        assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message should be displayed")
        assertTrue(
            loginPage.getFlashMessage().contains("invalid"),
            "Flash message should contain invalid credentials message"
        )
    }

    @Test
    fun testInvalidCredentials() {
        // Navigate to HomePage and click on 'Form Authentication' link
        homePage.navigateTo()
        homePage.clickLinkByText("Form Authentication")

        // Verify we're on the Login page
        assertTrue(loginPage.isLoaded(), "Login page should be loaded")

        // Perform login with both username and password invalid
        loginPage.login("invaliduser", "invalidpassword")

        // Verify failed login
        assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message should be displayed")
        assertTrue(
            loginPage.getFlashMessage().contains("invalid"),
            "Flash message should contain invalid credentials message"
        )
    }
}
