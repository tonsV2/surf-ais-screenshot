package dk.surfstation.ais.capabilities

import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.DesiredCapabilities

fun firefoxCapabilities(): DesiredCapabilities {
    val options = FirefoxOptions()
    options.addArguments("--window-size=1600,1200")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)

    return capabilities
}
