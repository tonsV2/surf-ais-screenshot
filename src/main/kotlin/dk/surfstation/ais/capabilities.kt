package dk.surfstation.ais

import dk.surfstation.ais.Browser.*
import org.openqa.selenium.Capabilities
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.DesiredCapabilities

enum class Browser {
    CHROME, FIREFOX
}

fun capabilities(browser: Browser): Capabilities = when (browser) {
    CHROME -> chromeCapabilities()
    FIREFOX -> firefoxCapabilities()
}

fun chromeCapabilities(): DesiredCapabilities {
    val options = ChromeOptions()

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)

    return capabilities
}

fun firefoxCapabilities(): DesiredCapabilities {
    val options = FirefoxOptions()

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)

    return capabilities
}
