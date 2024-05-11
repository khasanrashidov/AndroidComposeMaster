package com.example.sideeffect2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.sideeffect2.ui.theme.ComposeMasterTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    var count by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    var job: Job? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = count) {
        job = scope.launch {
            while (true) {
                delay(1000) // Wait for 1 second
                count++ // Increment the count
            }
        }
    }

    DisposableEffect(key1 = count) {
        onDispose {
            // Cleanup logic: Cancel the coroutine and print a message
            job?.cancel()
            println("Coroutine for incrementing count has been canceled.")
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Count: $count",
            style = TextStyle(fontSize = 32.sp)
            )
            Button(onClick = { scope.launch { count = 0 } }) {
                Text("Reset count",
                    style = TextStyle(fontSize = 32.sp)
                )
            }
        }
    }
}
