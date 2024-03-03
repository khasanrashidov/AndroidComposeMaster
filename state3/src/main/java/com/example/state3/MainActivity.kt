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

/**
 * Demonstrates various ways of using `remember` in Jetpack Compose for state management.
 * This includes utilizing `mutableStateOf`, employing a custom state class, and handling multiple state values.
 *
 * - [remember with mutableStateOf]: Utilizes `mutableStateOf` to create a single observable state that can be read and written.
 *   This is the simplest form of state in Compose, suitable for straightforward state management needs. The state is kept alive
 *   and consistent across recompositions, ensuring the UI reflects the current state.
 *
 * - [remember with a custom state class]: Demonstrates how to encapsulate state logic within a custom class. This approach
 *   is beneficial for more complex state management that might require additional logic or operations on the data. By using a
 *   custom class, you can keep your UI code clean and focused on presentation while the state class handles the specifics of
 *   state manipulation.
 *
 * - [remember with multiple state values]: Shows how to manage multiple related state values using `remember` and `mutableStateOf`.
 *   This is useful when your composable needs to manage more than one piece of state that might depend on each other or when
 *   you want to group state logic together. Destructuring declarations are used to unpack the state and its update function for
 *   cleaner code.
 *
 * Each of these approaches is showcased within a `CounterApp` composable function, where `CounterButton` composables are used
 * to increment different counters. This illustrates the flexibility of state management in Compose, allowing developers to choose
 * the most appropriate method for their use case.
 *
 * Example usage within `CounterApp`:
 *
 * 1. A simple counter using `remember` with `mutableStateOf`.
 * 2. A counter with encapsulated logic within a custom state class.
 * 3. A counter that utilizes multiple state values to increment by a dynamic step.
 *
 * This example highlights the power of Jetpack Compose in building interactive UIs with clean, maintainable state management.
 */





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
        //In Jetpack Compose, when you create a state using mutableStateOf,
        // you actually get a pair: the current value of the state and a function
        // to update that value. This pair is typically unpacked using Kotlin's
        // destructuring declaration.
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
