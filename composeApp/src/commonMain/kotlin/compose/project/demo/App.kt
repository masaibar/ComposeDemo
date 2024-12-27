package compose.project.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(countries: List<Country> = countries()) {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            onClick = {
                                timeAtLocation = currentTimeAt(country)
                                showCountries = false
                            }
                        ) {
                            Text(country.name)
                        }
                    }
                }
            }

            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {
                    showCountries = !showCountries
                }
            ) {
                Text("Select Location")
            }
        }
    }
}

data class Country(
    val name: String,
    val zone: TimeZone
)

fun countries(): List<Country> = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo")),
    Country("France", TimeZone.of("Europe/Paris")),
    Country("Mexico", TimeZone.of("America/Mexico_City")),
    Country("Indonesia", TimeZone.of("Asia/Jakarta")),
    Country("Egypt", TimeZone.of("Africa/Cairo"))
)

fun currentTimeAt(country: Country): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(country.zone).time
    return "The time in ${country.name} is ${localTime.formatted()}"
}
