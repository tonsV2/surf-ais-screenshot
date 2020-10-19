package dk.surfstation.ais

import java.io.File
import kotlin.system.exitProcess

fun main() {
    // Only for testing using docker compose. When deployed to Kubernetes Selenium Hub will be running indefinitely
    Thread.sleep(10 * 1000)

    val outputPath = "./data/screenshot.png"

    val aisUsername = System.getenv("AIS_USER") ?: throw EnvironmentVariableNotFoundException("AIS_USER")
    val aisPassword = System.getenv("AIS_PASS") ?: throw EnvironmentVariableNotFoundException("AIS_PASS")

    val aisUrl = "https://web.ais.dk/aisweb/?u=$aisUsername&p=$aisPassword"

    val aisPage = AisPage()

    aisPage.loadPage(aisUrl)
    aisPage.acceptCookies()
    aisPage.login()

    val screenshot = aisPage.screenshot()
    File(outputPath).writeBytes(screenshot)

    aisPage.close()

    exitProcess(0)
}

open class EnvironmentVariableNotFoundException(variable: String) :
    RuntimeException("Environment variable not found ($variable)")
