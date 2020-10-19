package dk.surfstation.ais

import dk.surfstation.ais.Browser.*
import org.openqa.selenium.Capabilities
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.opera.OperaOptions
import org.openqa.selenium.remote.DesiredCapabilities

enum class Browser {
    CHROME, FIREFOX, OPERA
}

fun capabilities(browser: Browser): Capabilities = when (browser) {
    CHROME -> chromeCapabilities()
    FIREFOX -> firefoxCapabilities()
    OPERA -> operaCapabilities()
}

fun chromeCapabilities(): DesiredCapabilities {
    val options = ChromeOptions()
//    options.setHeadless(true)
    options.addArguments("--window-size=1600,1200")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)

    return capabilities
}

fun firefoxCapabilities(): DesiredCapabilities {
    val options = FirefoxOptions()
    options.addArguments("--window-size=1600,1200")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)

    return capabilities
}

fun operaCapabilities(): DesiredCapabilities {
    val options = OperaOptions()
    options.addArguments("--window-size=1600,1200")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(OperaOptions.CAPABILITY, options)

    return capabilities
}
