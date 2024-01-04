package com.example.lifecycle2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            CounterWithButton()
            LifecycleAwareComponent()
        }
    }
}

@Composable
fun CounterWithButton() {
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { counter++ }) {
            Text("Increment Counter")
        }
        Text(text = "Counter: $counter", Modifier.padding(top = 16.dp))
    }
}

@Composable
fun LifecycleAwareComponent() {
    val coroutineScope = rememberCoroutineScope()
    var status by remember { mutableStateOf("Preparing...") }

    LaunchedEffect(Unit) {
        status = "Started"
        delay(5000) // Simulate long-running operation
        status = "Completed"
    }

    DisposableEffect(Unit) {
        onDispose {
            coroutineScope.launch {
                status = "Disposed"
            }
        }
    }

    Text(text = "Status: $status", Modifier.padding(top = 16.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
