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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    viewModel: AppViewModel = viewModel { AppViewModel() },
) {
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = uiState.selectedCountry?.currentTimeAt() ?: "No location selected",
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
                        viewModel.onAction(Action.OnDropdownMenuDismiss)
                    }
                ) {
                    uiState.countries.forEach { country ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onAction(
                                    Action.OnCountrySelected(country)
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
                    viewModel.onAction(Action.OnSelectLocationClick)
                }
            ) {
                Text("Select Location")
            }
        }
    }
}
