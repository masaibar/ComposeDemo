package compose.project.demo.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import compose.project.demo.ui.main.mainScreen
import compose.project.demo.ui.main.mainScreenRoute
import compose.project.demo.ui.sub.redScreen
import compose.project.demo.ui.sub.redScreenRoute

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = mainScreenRoute
    ) {
        mainScreen(
            onRedClick = { navController.navigate(redScreenRoute) },
        )
        redScreen()
    }
}
