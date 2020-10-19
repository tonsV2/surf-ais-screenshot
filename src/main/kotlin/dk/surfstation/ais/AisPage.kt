package dk.surfstation.ais

import dk.surfstation.ais.Browser.CHROME
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.Closeable
import java.net.URL
import java.time.Duration

class AisPage(seleniumHost: String) : Closeable {
    private val seleniumUrl = "http://$seleniumHost:4444/wd/hub"
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
        return driver.getScreenshotAs(OutputType.BYTES)
    }

    override fun close() {
        driver.close()
        driver.quit()
    }
}
