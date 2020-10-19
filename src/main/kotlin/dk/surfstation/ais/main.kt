package dk.surfstation.ais

import dk.surfstation.ais.capabilities.chromeCapabilities
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.net.URL
import java.time.Duration
import kotlin.system.exitProcess

fun main() {
//    val seleniumUrl = "http://selenium:4444/wd/hub"
    val seleniumUrl = "http://localhost:4444/wd/hub"

    val outputPath = "./data/screenshot.png"

    val aisUsername = System.getenv("AIS_USER") ?: throw EnvironmentVariableNotFoundException("AIS_USER")
    val aisPassword = System.getenv("AIS_PASS") ?: throw EnvironmentVariableNotFoundException("AIS_PASS")

    val aisUrl = "https://web.ais.dk/aisweb/?u=$aisUsername&p=$aisPassword"
    println(aisUrl)

    val capabilities = chromeCapabilities()
    val driver = RemoteWebDriver(URL(seleniumUrl), capabilities)

    driver.get(aisUrl)

    val timeOutInSeconds = Duration.ofSeconds(10)

    WebDriverWait(driver, timeOutInSeconds).until {
        it.findElement(By.id("button-1037-btnEl"))
    }

    val acceptCookiesButton = driver.findElement(By.id("button-1037-btnEl"))
    acceptCookiesButton.click()
    println("Cookies accepted")

    WebDriverWait(driver, timeOutInSeconds).until {
        it.findElement(By.id("login-button-id-btnWrap"))
    }

    val loginButton = driver.findElement(By.id("login-button-id-btnWrap"))
    loginButton.click()
    println("Login button clicked")

// Not the recommended way to wait! But to save time this is how I'll wait for all ships to appear
    Thread.sleep(10 * 1000)
    val screenshot = driver.getScreenshotAs(OutputType.BYTES)
    File(outputPath).writeBytes(screenshot)

    driver.close()
    driver.quit()

    println("Done!")
    exitProcess(0)
}

open class EnvironmentVariableNotFoundException(variable: String) :
    RuntimeException("Environment variable not found ($variable)")
