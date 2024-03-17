//20230329
// Prof. H. Jeong
// Include navigate, popBackStack inclusive false and true cases
//NavigationExample sets up a NavHost with three screens: Screen1, Screen2, and Screen3.
//Screen1 has a button that navigates to Screen2 when clicked.
//Screen2 has two buttons: one that pops back to Screen1 with inclusive = false, and another that navigates to Screen3.
//Screen3 has a button that pops back to Screen1 with inclusive = true.

package com.example.navigation5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation5.ui.theme.ComposeMasterTheme

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


@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController) }
        composable("screen2") { Screen2(navController) }
        composable("screen3") { Screen3(navController) }
    }
}

@Composable
fun Screen1(navController: NavHostController) {
    Button(onClick = { navController.navigate("screen2") }) {
        Text("Navigate to Screen 2")
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    Column {
        Button(onClick = { navController.popBackStack("screen1", false) }) {
            Text("Pop back to Screen 1 (inclusive = false)")
        }
        Button(onClick = { navController.navigate("screen3") }) {
            Text("Navigate to Screen 3")
        }
    }
}

@Composable
fun Screen3(navController: NavHostController) {
    Column {
        Button(onClick = { navController.popBackStack("screen1", false) }) {
            Text("Pop back to Screen 1 (inclusive = false)")
        }
        Button(onClick = { navController.popBackStack("screen1", true) }) {
            Text("Pop back to Screen 1 (inclusive = true)")
        }

    }

}
