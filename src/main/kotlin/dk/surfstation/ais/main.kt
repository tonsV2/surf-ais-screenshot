package dk.surfstation.ais

import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.opera.OperaOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.net.URL
import java.time.Duration

fun main() {
    //val seleniumUrl = "http://selenium:4444/wd/hub"
    val seleniumUrl = "http://localhost:4444/wd/hub"

    val aisUsername = System.getenv("AIS_USER") ?: throw EnvironmentVariableNotFoundException("AIS_USER")
    val aisPassword = System.getenv("AIS_PASS") ?: throw EnvironmentVariableNotFoundException("AIS_PASS")

    val aisUrl = "https://web.ais.dk/aisweb/?u=$aisUsername&p=$aisPassword"
    //val outputPath = "/data/screenshot.png"
    val outputPath = "./screenshot.png"

    val capabilities = chromeCapabilities()
    val driver = RemoteWebDriver(URL(seleniumUrl), capabilities)

//    val driver: WebDriver = ChromeDriver()
    driver.get(aisUrl)

    println("Wait")
//    Thread.sleep(3 * 1000)
//    WebDriverWait(driver, Duration.ofSeconds(10))
    val webDriverWait = WebDriverWait(driver, Duration.ofSeconds(10)).until {
        it.findElement(By.id("button-1037-btnEl"))
    }
/*
    webDriverWait.until {
        (it as JavascriptExecutor).executeScript("return document.readyState") == "complete"
    }
*/
    println("Wait")

//    Thread.sleep(5 * 1000)
    val acceptCookiesButton = driver.findElement(By.id("button-1037-btnEl"))
    acceptCookiesButton.click()

    //Thread.sleep(5 * 1000)
    WebDriverWait(driver, Duration.ofSeconds(10)).until {
        it.findElement(By.id("login-button-id-btnWrap"))
    }

    val loginButton = driver.findElement(By.id("login-button-id-btnWrap"))
    loginButton.click()

    Thread.sleep(5 * 1000)
    Thread.sleep(5 * 1000)
    val screenshot = (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
    File(outputPath).writeBytes(screenshot)

    driver.close()
    println("Done!")
}

private fun firefoxCapabilities(): DesiredCapabilities {
    val options = FirefoxOptions()
    options.addArguments("--window-size=1600,1200")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)

    return capabilities
}

private fun operaCapabilities(): DesiredCapabilities {
    val options = OperaOptions()
    options.addArguments("--window-size=1600,1200")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(OperaOptions.CAPABILITY, options)

    return capabilities
}

private fun chromeCapabilities(): DesiredCapabilities {
    val options = ChromeOptions()
    options.addArguments("--window-size=1600,1200", "--ignore-certificate-errors")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)

    return capabilities
}

open class EnvironmentVariableNotFoundException(variable: String) : RuntimeException("Environment variable not found ($variable)")
