package compose.project.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        Column {
            Text(timeAtLocation)
            Button(
                onClick = { timeAtLocation = "13:30" }
            ) {
                Text("Show Time At Location")
            }
        }
    }
}
