package compose.project.demo

import androidx.compose.ui.window.ComposeUIViewController
import compose.project.demo.di.Koin
import compose.project.demo.ui.main.MainScreen

fun MainViewController() = ComposeUIViewController(
    configure = {
        Koin.init()
    }
) { MainScreen() }
