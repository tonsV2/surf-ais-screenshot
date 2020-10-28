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
        val acceptCookiesButtonId = By.id("button-1037-btnEl")

        WebDriverWait(driver, timeOutInSeconds).until {
            it.findElement(acceptCookiesButtonId)
        }

        val acceptCookiesButton = driver.findElement(acceptCookiesButtonId)
        acceptCookiesButton.click()
    }

    fun login() {
        val loginButtonId = By.id("login-button-id-btnWrap")

        WebDriverWait(driver, timeOutInSeconds).until {
            it.findElement(loginButtonId)
        }

        val loginButton = driver.findElement(loginButtonId)
        loginButton.click()
    }

    fun screenshot(): ByteArray {
        return driver.getScreenshotAs(OutputType.BYTES)
    }

    override fun close() {
        driver.close()
        driver.quit()
    }
}
