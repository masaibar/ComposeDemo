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
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.eg
import composedemo.composeapp.generated.resources.fr
import composedemo.composeapp.generated.resources.id
import composedemo.composeapp.generated.resources.jp
import composedemo.composeapp.generated.resources.mx
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    viewModel: AppViewModel = viewModel { AppViewModel() },
    countries: List<Country> = countries()
) {
    val uiState = viewModel.uiState.collectAsState()

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
                    expanded = uiState.value.showCountries,
                    onDismissRequest = {
                        viewModel.onAction(
                            Action.OnCountriesVisibilityToggle(
                                isVisible = false
                            )
                        )
                    }
                ) {
                    countries.forEach { country ->
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
                            isVisible = uiState.value.showCountries.not()
                        )
                    )
                }
            ) {
                Text("Select Location")
            }
        }
    }
}

data class Country(
    val name: String,
    val zone: TimeZone,
    val image: DrawableResource
)

fun countries(): List<Country> = listOf(
    Country(
        name = "Japan",
        zone = TimeZone.of("Asia/Tokyo"),
        image = Res.drawable.jp
    ),
    Country(
        name = "France",
        zone = TimeZone.of("Europe/Paris"),
        image = Res.drawable.fr
    ),
    Country(
        name = "Mexico",
        zone = TimeZone.of("America/Mexico_City"),
        image = Res.drawable.mx
    ),
    Country(
        name = "Indonesia",
        zone = TimeZone.of("Asia/Jakarta"),
        image = Res.drawable.id
    ),
    Country(
        name = "Egypt",
        zone = TimeZone.of("Africa/Cairo"),
        image = Res.drawable.eg
    )
)

fun currentTimeAt(country: Country): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(country.zone).time
    return "The time in ${country.name} is ${localTime.formatted()}"
}
