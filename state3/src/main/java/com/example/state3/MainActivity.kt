// 2023 Prof. H. Jeong
// 1. remember with mutableStateOf
// 2. remember with a custom state class
// 3. remember with multiple state values

package com.example.state3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.state3.ui.theme.ComposeMasterTheme

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
                    CounterApp()
                }
            }
        }
    }
}


@Composable
fun CounterApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Various types of remember", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(16.dp))

        // 1. remember with mutableStateOf
        var counter1 by remember { mutableStateOf(0) }
        CounterButton(
            label = "Counter 1 (remember with mutableStateOf)",
            count = counter1,
            increment = { counter1++ }
        )

        // 2. remember with custom state class
        val customCounterState = remember { CustomCounterState() }
        CounterButton(
            label = "Counter 2 (remember with custom state class)",
            count = customCounterState.count,
            increment = { customCounterState.increment() }
        )

        // 3. remember with multiple state values
        val (counter3, setCounter3) = remember { mutableStateOf(0) }
        val (step, setStep) = remember { mutableStateOf(2) }
        CounterButton(
            label = "Counter 3 (remember with multiple state values, step: $step)",
            count = counter3,
            increment = { setCounter3(counter3 + step) }
        )
    }
}

class CustomCounterState {
    private var _count by mutableStateOf(0)
    val count: Int get() = _count

    fun increment() {
        _count++
    }
}

@Composable
fun CounterButton(label: String, count: Int, increment: () -> Unit) {
    Button(
        onClick = increment,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text("$label: $count")
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    CounterApp()
}
