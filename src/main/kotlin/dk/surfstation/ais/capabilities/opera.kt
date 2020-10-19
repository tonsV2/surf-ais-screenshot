package dk.surfstation.ais.capabilities

import org.openqa.selenium.opera.OperaOptions
import org.openqa.selenium.remote.DesiredCapabilities

fun operaCapabilities(): DesiredCapabilities {
    val options = OperaOptions()
    options.addArguments("--window-size=1600,1200")

    val capabilities = DesiredCapabilities()
    capabilities.setCapability(OperaOptions.CAPABILITY, options)

    return capabilities
}
