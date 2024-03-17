package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigation.ui.theme.ComposeMasterTheme


/**
 * General Guidelines for Navigation in Jetpack Compose
 * ![Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
 * Navigation in Jetpack Compose is essential for building multi-screen applications.
 * Following these general guidelines will help ensure your navigation logic is both
 * efficient and maintainable.
 *
 * Best Practices:
 * - Define a single [NavHost] per application or navigation area. The NavHost is responsible
 *   for managing your navigation graph, which defines all possible navigation routes in your app.
 *
 * - Use named routes and arguments for navigation. Defining constants for route names
 *   helps prevent errors due to typos and makes your navigation logic more readable.
 *
 * - Prefer passing data between screens using navigation arguments rather than ViewModel
 *   shared between composables. This approach makes your navigation state explicit and your app
 *   more modular.
 *
 * - Consider using Deep Links for navigation routes that should be accessible from outside
 *   the app (e.g., from a notification or a web link). Compose Navigation supports deep linking
 *   out of the box, allowing you to define URI patterns that match your navigation routes.
 *
 * Performance Considerations:
 * - Avoid recomposition of the entire [NavHost] when navigating between routes. Make sure
 *   that the state leading to recomposition is scoped as close to the individual composable
 *   screens as possible.
 *
 * - Use [rememberSaveable] to preserve the state of individual screens across configuration
 *   changes or process death. This is especially important for user input fields or other
 *   transient state.
 *
 * Common Patterns:
 * - Modular Navigation: Structure your navigation graph in a modular way, especially for
 *   larger apps. Consider defining separate navigation graphs for different features or
 *   user flows and combining them using [NavGraphBuilder.include] function.
 *
 * - Conditional Navigation: Implement conditional logic within your navigation actions
 *   to handle scenarios such as authentication gates or feature flags. Use the [NavController]
 *   to programmatically decide the navigation route at runtime.
 *
 * - Navigation with Results: For scenarios where a screen needs to return a result to its
 *   caller, use a shared ViewModel or a custom back stack entry listener to pass back data.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val DETAILS_ROUTE = "details"
    const val DETAILS_ARGUMENT = "itemId"
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = MainDestinations.HOME_ROUTE) {
        composable(MainDestinations.HOME_ROUTE) {
            HomeScreen(navController)
        }
        composable(
            "${MainDestinations.DETAILS_ROUTE}/{${MainDestinations.DETAILS_ARGUMENT}}",
            arguments = listOf(navArgument(MainDestinations.DETAILS_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(MainDestinations.DETAILS_ARGUMENT)
            itemId?.let {
                DetailsScreen(itemId, navController)
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen")
        Button(onClick = { navController.navigate("${MainDestinations.DETAILS_ROUTE}/1") }) {
            Text("Go to Details (Item 1)")
        }
        Button(onClick = { navController.navigate("${MainDestinations.DETAILS_ROUTE}/2") }) {
            Text("Go to Details (Item 2)")
        }
    }
}

@Composable
fun DetailsScreen(itemId: Int, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Details Screen for Item $itemId")
        BackHandler { navController.popBackStack() }
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}
