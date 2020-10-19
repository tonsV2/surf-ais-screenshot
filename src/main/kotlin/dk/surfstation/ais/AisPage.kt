package dk.surfstation.ais

import dk.surfstation.ais.Browser.CHROME
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL
import java.time.Duration

class AisPage {
    private val seleniumUrl = "http://selenium:4444/wd/hub"
    private val timeOutInSeconds = Duration.ofSeconds(10)

    private var driver: RemoteWebDriver

    init {
        val capabilities = capabilities(CHROME)
        driver = RemoteWebDriver(URL(seleniumUrl), capabilities)
    }

    fun loadPage(url: String) {
        driver.get(url)
    }

    fun acceptCookies() {
        WebDriverWait(driver, timeOutInSeconds).until {
            it.findElement(By.id("button-1037-btnEl"))
        }

        val acceptCookiesButton = driver.findElement(By.id("button-1037-btnEl"))
        acceptCookiesButton.click()
        println("Cookies accepted")
    }

    fun login() {
        WebDriverWait(driver, timeOutInSeconds).until {
            it.findElement(By.id("login-button-id-btnWrap"))
        }

        val loginButton = driver.findElement(By.id("login-button-id-btnWrap"))
        loginButton.click()
        println("Login button clicked")
    }

    fun screenshot(): ByteArray {
        // Not the recommended way to wait! But to save time this is how I'll wait for all ships to appear
        Thread.sleep(10 * 1000)
        return driver.getScreenshotAs(OutputType.BYTES)
    }

    fun close() {
        driver.close()
        driver.quit()
    }
}
