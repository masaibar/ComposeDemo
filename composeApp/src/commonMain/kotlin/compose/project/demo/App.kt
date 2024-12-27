package compose.project.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import compose.project.demo.domain.country.Country
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    viewModel: AppViewModel = viewModel { AppViewModel() },
) {
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme {
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier.padding(
                    start = 20.dp,
                    top = 10.dp
                )
            ) {
                DropdownMenu(
                    expanded = uiState.showCountries,
                    onDismissRequest = {
                        viewModel.onAction(
                            Action.OnCountriesVisibilityToggle(
                                isVisible = false
                            )
                        )
                    }
                ) {
                    uiState.countries.forEach { country ->
                        DropdownMenuItem(
                            onClick = {
                                timeAtLocation = currentTimeAt(country)
                                viewModel.onAction(
                                    Action.OnCountriesVisibilityToggle(
                                        isVisible = false
                                    )
                                )
                            }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(country.image),
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(10.dp),
                                    contentDescription = "flag"
                                )
                                Text(country.name)
                            }
                        }
                    }
                }
            }

            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {
                    viewModel.onAction(
                        Action.OnCountriesVisibilityToggle(
                            isVisible = uiState.showCountries.not()
                        )
                    )
                }
            ) {
                Text("Select Location")
            }
        }
    }
}

fun currentTimeAt(country: Country): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(country.zone).time
    return "The time in ${country.name} is ${localTime.formatted()}"
}
