package com.example.dimanual

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
import com.example.dimanual.ui.theme.DependencyInjectionTheme


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
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    GreetingScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingScreen() {
    val greetingRepository = GreetingRepositoryImpl()
    val greetingViewModel = GreetingViewModel(greetingRepository)

    var inputName by remember { mutableStateOf("") }

    Column {
        TextField(
            value = inputName,
            onValueChange = { inputName = it },
            label = { Text("Enter your name") }
        )
        Button(onClick = { greetingViewModel.updateGreeting(inputName) }) {
            Text("Submit")
        }

        val greeting by greetingViewModel.greeting.observeAsState()

        greeting?.let { Text(text = it) }
    }
}
