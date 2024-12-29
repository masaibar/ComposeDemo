package compose.project.demo.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

internal const val mainScreenRoute = "main"

fun NavGraphBuilder.mainNavGraph() {
    composable(mainScreenRoute) {
        MainScreen()
    }
}

@Composable
@Preview
fun MainScreen() {
    KoinContext {
        val viewModel = koinViewModel<MainViewmodel>()
        val uiState by viewModel.uiState.collectAsState()

        MainScreen(
            uiState = uiState,
            onAction = viewModel::onAction
        )
    }
}

@Composable
fun MainScreen(
    uiState: UiState,
    onAction: (Action) -> Unit
) {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = uiState.greeting.greet()
            )
            Text(
                text = uiState.selectedCountry?.currentTimeAt() ?: "No location selected",
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenu(
                    expanded = uiState.showCountries,
                    onDismissRequest = {
                        onAction(Action.OnDropdownMenuDismiss)
                    }
                ) {
                    uiState.countries.forEach { country ->
                        DropdownMenuItem(
                            onClick = {
                                onAction(Action.OnCountrySelected(country))
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
                onClick = {
                    onAction(Action.OnSelectLocationClick)
                }
            ) {
                Text("Select Location")
            }
        }
    }
}
