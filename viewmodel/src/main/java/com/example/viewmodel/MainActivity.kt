//1. Defining a ViewModel class with mutable state properties and methods to manipulate the state.
//2. Using the viewModel() function to create an instance of the ViewModel within a composable.
//3. Accessing the ViewModel instance within composable functions to display and modify state.

package com.example.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodel.ui.theme.ComposeMasterTheme

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
    val counterViewModel = viewModel<CounterViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Counter App") })
        },
        content = {paddingValues ->
            Modifier.padding(paddingValues)
            CounterScreen(counterViewModel)
        }
    )
}

@Composable
fun CounterScreen(counterViewModel: CounterViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: ${counterViewModel.count.value}")

        Button(onClick = { counterViewModel.increment() }) {
            Text("Increment")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { counterViewModel.decrement() }) {
            Text("Decrement")
        }
    }
}
