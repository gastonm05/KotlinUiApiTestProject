package ui

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.junit.jupiter.api.Assertions.assertTrue

class HomePageTest : BaseTest() {

    private lateinit var homePage: HomePage

    @BeforeEach
    override fun setUp() {
        super.setUp()
        homePage = HomePage(driver)
    }
    
    @Test
    fun testHeaderIsDisplayed() {
        homePage.openPage()
        val headerText = homePage.getHeaderText()
        assertTrue(headerText.isNotEmpty(), "Header should be displayed and contain text")
    }
}