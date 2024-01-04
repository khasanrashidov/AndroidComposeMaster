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
