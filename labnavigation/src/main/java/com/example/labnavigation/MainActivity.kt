package com.example.labnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labnavigation.ui.theme.ComposeMasterTheme
import kotlinx.coroutines.delay
import kotlin.random.Random


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
    Scaffold(
        topBar = { MyTopBar() },
        bottomBar = { MyBottomBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen() }
                composable("game") { GameScreen() }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("Mobile Programming") },
        actions = {
            IconButton(onClick = { /* Handle settings click */ }) {
                Icon(Icons.Filled.Settings, contentDescription = "Settings")
            }
        }
    )
}

@Composable
fun MyBottomBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "Game") },
            label = { Text("Game") },
            selected = false,
            onClick = { navController.navigate("game") }
        )
        NavigationBarItem(
            selected = false,
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search" )},
            onClick = { /*TODO*/ },
            label = { Text("Search") },
//            onClickLabel = { /*TODO*/ }
        )
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Assuming the image file is named hong_jeong_photo.jpg and placed in res/drawable
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.hongjeong),
                contentDescription = "Hong Jeong",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text("Hong Jeong")
                Text("Researcher")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text("Careers")
        // Add a LazyColumn with random sentences
        // Add a LazyColumn with computer-related careers
        val careers = listOf(
            "Software Developer",
            "Database Administrator",
            "Computer Hardware Engineer",
            "Computer Systems Analyst",
            "Computer Network Architect",
            "Web Developer",
            "Information Security Analyst",
            "Computer and Information Research Scientists",
            "Computer and Information Systems Managers",
            "IT Project Manager"
            // Add more careers as needed
        )
        LazyColumn {
            items(careers) { career ->
                Text(career)
            }
        }

    }
}

@Composable
fun GameScreen() {
    var targetNumber by remember { mutableStateOf(Random.nextInt(1, 101)) }
    var userGuess by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("Guess a number between 1 and 100") }
    var questionCount by remember { mutableStateOf(0) }
    var gameFinished by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = feedback)
        if (!gameFinished) {
            OutlinedTextField(
                value = userGuess,
                onValueChange = { userGuess = it },
                label = { Text("Your Guess") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    val guess = userGuess.toIntOrNull()
                    if (guess != null) {
                        questionCount++
                        when {
                            questionCount >= 20 -> {
                                feedback = "Game Over. The number was $targetNumber. Restarting..."
                                gameFinished = true
                            }
                            guess < targetNumber -> feedback = "Too low! Try again."
                            guess > targetNumber -> feedback = "Too high! Try again."
                            else -> {
                                feedback = "Congratulations! You've guessed it in $questionCount guesses. Restarting..."
                                gameFinished = true
                            }
                        }

                    } else {
                        feedback = "Please enter a valid number."
                    }
                    userGuess = "" // Clear the input field
                })
            )
        }
        Button(
            onClick = {
                // Reset everything for a new game
                targetNumber = Random.nextInt(1, 101)
                questionCount = 0
                gameFinished = false
                feedback = "Guess a number between 1 and 100"
                userGuess = ""
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Restart Game")
        }
        if (gameFinished) {
            LaunchedEffect(feedback) {
                delay(3000)
                targetNumber = Random.nextInt(1, 101)
                questionCount = 0
                gameFinished = false
                feedback = "Guess a number between 1 and 100"
            }
        }
    }
}

// Reuse the Greeting composable for the HomeScreen preview
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMasterTheme {
        Greeting("Android")
    }
}