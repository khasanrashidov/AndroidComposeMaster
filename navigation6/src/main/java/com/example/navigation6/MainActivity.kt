package com.example.navigation6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation6.ui.theme.ComposeMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
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
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        // Dynamically generate question screens
        (1..3).forEach { questionNumber ->
            composable("question/$questionNumber") { QuestionScreen(navController, questionNumber) }
        }
        composable("results") { ResultsScreen(navController) }
    }
}

@Composable
fun WelcomeScreen(navController: NavController) {
    // Display welcome message and a button to start the quiz
    Button(onClick = { navController.navigate("question/1") }) {
        Text("Start Quiz")
    }
}

@Composable
fun QuestionScreen(navController: NavController, questionNumber: Int) {
    Column {
        Text("Question $questionNumber")
        Button(onClick = { navController.navigate("question/${questionNumber + 1}") }) {
            Text("Next Question")
        }
        if (questionNumber > 1) {
            Button(onClick = { navController.popBackStack("welcome", inclusive = false) }) {
                Text("Back to Welcome")
            }
        }
        Button(onClick = { navController.navigate("results") }) {
            Text("Skip to Results")
        }
    }
}

@Composable
fun ResultsScreen(navController: NavController) {
    Column {
        Text("Quiz Results")
        Button(onClick = { navController.popBackStack("welcome", inclusive = false) }) {
            Text("Restart Quiz")
        }
    }
}
