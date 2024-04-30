package com.example.dihiltmethod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.dihiltmethod.ui.theme.DependencyInjectionTheme


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
    // Constructor injection
    // GreetingScreen composable function injects dependencies into GreetingViewModel
    // Create an instance of GreetingRepository
    val greetingRepository = GreetingRepository()
    // Create an instance of GreetingViewModel depending on GreetingRepository
    val greetingViewModel = GreetingViewModel(greetingRepository)

    var inputName by remember { mutableStateOf("") }
    val greeting by greetingViewModel.greeting.observeAsState()

    Column {
        TextField(
            value = inputName,
            onValueChange = { inputName = it },
            label = { Text("Enter your name") }
        )
        Button(onClick = { greetingViewModel.updateGreeting(inputName) }) {
            Text("Submit")
        }


        greeting?.let { Text(text = it) }
    }
}
