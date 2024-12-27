package compose.project.demo

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import compose.project.demo.di.Koin
import compose.project.demo.ui.HomeScreen

@JsModule("@js-joda/timezone")
external object JsJodaTimeZoneModule

private val jsJodaTz = JsJodaTimeZoneModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    Koin.init()

    ComposeViewport(
        viewportContainerId = "composeApplication"
    ) {
        HomeScreen()
    }
}
