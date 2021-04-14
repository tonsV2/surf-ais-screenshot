package dk.surfstation.ais

import java.io.File
import kotlin.system.exitProcess

fun main() {
    // Only for testing using docker compose. When deployed to Kubernetes Selenium Hub will be running indefinitely
    //Thread.sleep(10 * 1000)

    val outputPath = System.getenv("OUTPUT_PATH") ?: throw EnvironmentVariableNotFoundException("OUTPUT_PATH")

    val seleniumHost = System.getenv("SELENIUM_HOST") ?: throw EnvironmentVariableNotFoundException("SELENIUM_HOST")

    val aisUsername = System.getenv("AIS_USER") ?: throw EnvironmentVariableNotFoundException("AIS_USER")
    val aisPassword = System.getenv("AIS_PASS") ?: throw EnvironmentVariableNotFoundException("AIS_PASS")

    val aisUrl = "https://web.ais.dk/aisweb/?u=$aisUsername&p=$aisPassword"

    val aisPage = AisPage(seleniumHost)

    try {
        aisPage.use { page ->
            page.loadPage(aisUrl)
            println("Page loaded")
            page.acceptCookies()
            println("Cookies accepted")
//            page.login()
//            println("Login button clicked")

            // Not the recommended way to wait! But due to the nature of the AIS website and to save time this is how I'll wait for all ships to appear
            Thread.sleep(20 * 1000)
            val screenshot = page.screenshot()
            File(outputPath).writeBytes(screenshot)
            println("Screenshot saved")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        exitProcess(1)
    }

    // Force exit... https://stackoverflow.com/questions/60059290/java-program-is-not-terminating-when-using-selenium-webdriver
    exitProcess(0)
}

open class EnvironmentVariableNotFoundException(variable: String) :
    RuntimeException("Environment variable not found ($variable)")
