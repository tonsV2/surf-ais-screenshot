package dk.surfstation.ais.capabilities

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

fun chromeCapabilities(): DesiredCapabilities {
    val options = ChromeOptions()
//    options.setHeadless(true)
    options.addArguments("--window-size=1600,1200")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)

    return capabilities
}
