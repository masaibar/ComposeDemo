package compose.project.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import compose.project.demo.domain.greeting.Greeting
import compose.project.demo.ui.AppNavHost
import compose.project.demo.ui.main.MainScreen
import compose.project.demo.ui.main.UiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenAndroidPreview() {
    MainScreen(
        uiState = UiState.initialValue(Greeting()),
        onAction = {},
        onRedClick = {}
    )
}
