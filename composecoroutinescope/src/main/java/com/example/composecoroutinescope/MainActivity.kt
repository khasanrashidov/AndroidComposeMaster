package com.example.composecoroutinescope

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composecoroutinescope.ui.theme.ComposeMasterTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope as rememberCoroutineScope

/**
 * # Compose Coroutine Scope
 * Demonstrates the use of `rememberCoroutineScope` to handle asynchronous tasks within Composable functions.
 * This example includes a Composable function that loads data asynchronously when a button is clicked.
 *
 * ## Features
 * - **Lifecycle Awareness**: The coroutine scope is automatically canceled when the Composable is disposed,
 *   aligning coroutine cancellation with the Composable's lifecycle.
 * - **Concurrency Management**: Manages concurrent operations within the Composable's scope effectively.
 *
 * ## Example Usage
 * This function renders a button that, when clicked, fetches data asynchronously. The data fetch is simulated
 * with a delay to mimic network latency. The coroutine is launched in a scope tied to the Composable's lifecycle,
 * ensuring that it's canceled if the Composable is removed from the UI tree, preventing memory leaks.
 *
 * @param onDataLoaded Callback function to handle the data once loaded.
 */


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMasterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

/**
 * Sets up the main application interface with components for user interaction.
 */
@Composable
fun MyApp() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        LoadDataComponent { data ->
            // Implementing a basic UI reaction to data loading.
            // For instance, displaying the data in a Toast or updating the UI state.
            println("Data received: $data")  // This can be replaced with actual UI logic.
            Toast.makeText(context, data, Toast.LENGTH_LONG).show()
        }
    }
}

/**
 * Composable that includes a button to load data when clicked.
 * Utilizes a coroutine scope remembered across recompositions.
 *
 * @param onDataLoaded Callback to handle data once loaded.
 */
@Composable
fun LoadDataComponent(onDataLoaded: (String) -> Unit) {
    val scope = rememberCoroutineScope()

    Button(onClick = {
        scope.launch {
            val data = fetchData()
            onDataLoaded(data)
        }
    }) {
        Text("Load Data")
    }
}

/**
 * Simulates an asynchronous data-fetching operation.
 *
 * @return A string result after a delay, mimicking a network call.
 */
suspend fun fetchData(): String {
    delay(1000)  // Simulate network delay
    return "Data loaded successfully"
}