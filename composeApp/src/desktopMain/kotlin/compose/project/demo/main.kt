package compose.project.demo

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import compose.project.demo.di.Koin
import compose.project.demo.ui.main.MainScreen

fun main() = application {
    Koin.init()

    val state = rememberWindowState(
        size = DpSize(400.dp, 250.dp),
        position = WindowPosition(300.dp, 300.dp)
    )
    Window(title = "Local Time App", onCloseRequest = ::exitApplication, state = state) {
        MainScreen()
    }
}
