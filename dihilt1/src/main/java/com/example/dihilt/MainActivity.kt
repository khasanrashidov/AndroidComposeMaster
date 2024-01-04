package com.example.dihilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dihilt.ui.theme.DependencyInjectionTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DependencyInjectionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingScreen()
                }
            }
        }
    }
}


@Composable
fun GreetingScreen() {
    // Hilt provides the ViewModel
    val greetingViewModel: GreetingViewModel = viewModel()

    // Observe the greeting LiveData as state
    val greeting by greetingViewModel.greeting.observeAsState()

    // Remember the user input
    var inputName by remember { mutableStateOf("") }

    Column {
        // TextField to get user input
        TextField(
            value = inputName,
            onValueChange = { inputName = it }, // Update inputName when user types
            label = { Text("Enter your name") }
        )

        // Button to submit user input
        Button(onClick = { greetingViewModel.updateGreeting(inputName) }) {
            Text("Submit")
        }

        // Display the greeting
        Text(text = greeting ?: "Enter a name...")
    }
}
