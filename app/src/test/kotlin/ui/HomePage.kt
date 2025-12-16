package ui

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class HomePage(private val driver: WebDriver) {

    companion object {
        private const val BASE_URL = "https://the-internet.herokuapp.com/"
        private val HEADING = By.className("heading") // Updated to match <h1 class="heading">
        private val HEADER = By.tagName("h2") // Updated to match <h2>Available Examples</h2>
        private val LINK_CONTAINER = By.tagName("ul") // Updated to match <ul> containing links
        private val LINKS = By.tagName("a") // Links are <a> tags inside the <ul>

        // Individual link locators
        private val AB_TESTING = By.linkText("A/B Testing")
        private val ADD_REMOVE_ELEMENTS = By.linkText("Add/Remove Elements")
        private val BASIC_AUTH = By.linkText("Basic Auth")
        private val BROKEN_IMAGES = By.linkText("Broken Images")
        private val CHALLENGING_DOM = By.linkText("Challenging DOM")
        private val CHECKBOXES = By.linkText("Checkboxes")
        private val CONTEXT_MENU = By.linkText("Context Menu")
        private val DIGEST_AUTH = By.linkText("Digest Authentication")
        private val DISAPPEARING_ELEMENTS = By.linkText("Disappearing Elements")
        private val DRAG_AND_DROP = By.linkText("Drag and Drop")
        private val DROPDOWN = By.linkText("Dropdown")
        private val DYNAMIC_CONTENT = By.linkText("Dynamic Content")
        private val DYNAMIC_CONTROLS = By.linkText("Dynamic Controls")
        private val DYNAMIC_LOADING = By.linkText("Dynamic Loading")
        private val ENTRY_AD = By.linkText("Entry Ad")
        private val EXIT_INTENT = By.linkText("Exit Intent")
        private val FILE_DOWNLOAD = By.linkText("File Download")
        private val FILE_UPLOAD = By.linkText("File Upload")
        private val FLOATING_MENU = By.linkText("Floating Menu")
        private val FORGOT_PASSWORD = By.linkText("Forgot Password")
        private val FORM_AUTHENTICATION = By.linkText("Form Authentication")
        private val FRAMES = By.linkText("Frames")
        private val GEOLOCATION = By.linkText("Geolocation")
        private val HORIZONTAL_SLIDER = By.linkText("Horizontal Slider")
        private val HOVERS = By.linkText("Hovers")
        private val INFINITE_SCROLL = By.linkText("Infinite Scroll")
        private val INPUTS = By.linkText("Inputs")
        private val JQUERY_UI_MENUS = By.linkText("JQuery UI Menus")
        private val JAVASCRIPT_ALERTS = By.linkText("JavaScript Alerts")
        private val JAVASCRIPT_ERROR = By.linkText("JavaScript onload event error")
        private val KEY_PRESSES = By.linkText("Key Presses")
        private val LARGE_DOM = By.linkText("Large & Deep DOM")
        private val MULTIPLE_WINDOWS = By.linkText("Multiple Windows")
        private val NESTED_FRAMES = By.linkText("Nested Frames")
        private val NOTIFICATION_MESSAGES = By.linkText("Notification Messages")
        private val REDIRECT_LINK = By.linkText("Redirect Link")
        private val SECURE_FILE_DOWNLOAD = By.linkText("Secure File Download")
        private val SHADOW_DOM = By.linkText("Shadow DOM")
        private val SHIFTING_CONTENT = By.linkText("Shifting Content")
        private val SLOW_RESOURCES = By.linkText("Slow Resources")
        private val SORTABLE_DATA_TABLES = By.linkText("Sortable Data Tables")
        private val STATUS_CODES = By.linkText("Status Codes")
        private val TYPOS = By.linkText("Typos")
        private val WYSIWYG_EDITOR = By.linkText("WYSIWYG Editor")
    }

    fun navigateTo() {
        driver.get(BASE_URL)
    }

    fun openPage() {
        navigateTo()
    }

    fun getPageHeading(): String {
        return driver.findElement(HEADING).text
    }

    fun getHeaderText(): String {
        return driver.findElement(HEADER).text
    }

    fun getAvailableLinks(): List<String> {
        return driver.findElement(LINK_CONTAINER)
            .findElements(LINKS)
            .map { it.text }
    }

    fun clickLinkByText(linkText: String) {
        driver.findElement(By.linkText(linkText)).click()
    }

    fun isLoaded(): Boolean {
        return try {
            driver.findElement(HEADING).isDisplayed
        } catch (e: Exception) {
            false
        }
    }
}