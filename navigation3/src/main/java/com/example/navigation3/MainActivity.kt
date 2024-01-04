package com.example.navigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.navigation3.ui.theme.ComposeMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    ComposeMasterTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            val navController = rememberNavController()
            val items = listOf("Home", "Profile", "Settings")
            val currentRoute = currentRoute(navController)

            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        items.forEach { item ->
                            BottomNavigationItem(
                                icon = { Icon(Icons.Default.Home, contentDescription = item) },
                                label = { Text(item) },
                                selected = currentRoute == item,
                                onClick = {
                                    if (item != currentRoute) {
                                        navController.navigate(item)
                                    }
                                }
                            )
                        }
                    }
                }
            ) {paddingValues -> Modifier.padding(paddingValues)
                NavHost(navController = navController, startDestination = "Home") {
                    composable("Home") { HomeScreen(navController) }
                    composable("Profile") { ProfileScreen(navController) }
                    composable("Settings") { SettingsScreen(navController) }
                    composable(
                        "Details/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id")
                        DetailsScreen(id)
                    }
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}



@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home Screen")
        Button(
            onClick = { navController.navigate("Details/42") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Navigate to Details with ID 42")
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profile Screen")
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Settings Screen")
    }
}

@Composable
fun DetailsScreen(id: Int?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Details Screen, ID: ${id ?: "unknown"}")
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp()
}

