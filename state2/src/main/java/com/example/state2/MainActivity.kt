package com.example.state2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.state2.ui.theme.ComposeMasterTheme


/**
 * **State Management in Jetpack Compose**
 * Demonstrates the use of state management in Jetpack Compose with `remember` and `rememberSaveable`,
 * and the difference between direct and delegated property access in Kotlin.
 *
 * - [remember]: A function that returns a value which will be preserved across recompositions.
 *   This is useful for maintaining state within a single composition. The state held by `remember`
 *   is not preserved through configuration changes or when the activity is restarted.
 *
 * - [rememberSaveable]: A specialized version of `remember` that persists the state across
 *   configuration changes or process death, such as when the app is backgrounded and later resumed.
 *   It uses the saved instance state mechanism to preserve values. `rememberSaveable` is particularly
 *   useful for retaining user input or other important transient UI state across such events.
 *
 * - `rememberSaveable` acts as a wrapper around `remember`, extending its functionality to include
 *   saving to the instance state. This ensures that critical UI state is not lost during app lifecycle
 *   events that trigger recomposition.
 *
 * - The distinction between "=" and "by" in Kotlin for state access:
 *   - "=" (equals): Used for directly accessing and assigning values. When you use "=", you're working
 *     directly with the state value itself. This is straightforward but requires explicit calls to state
 *     management functions for updates.
 *
 *   - "by" (delegated property): Enables delegated property access, which allows for more concise and
 *     idiomatic access to the value. When using "by", Kotlin delegates the get and set operations of the
 *     property to a specified delegate. In the context of Jetpack Compose, "by" is often used with
 *     `remember` or `rememberSaveable` to provide more seamless access to mutable state without needing
 *     to explicitly call methods on the state object. This results in cleaner and more readable code.
 *
 * Example Usage:
 *
 * With "=":
 * ```
 * var (counter, setCounter) = remember { mutableStateOf(0) }
 * setCounter(counter + 1)
 * ```
 *
 * With "by":
 * ```
 * var counter by remember { mutableStateOf(0) }
 * counter++
 * ```
 *
 * The use of "=" vs. "by" affects how you interact with state in your composables, offering flexibility
 * between explicit method calls and delegated property access for state management.
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
                    MyApp()
                }
            }
        }
    }
}

// Rotate screen to see the saving effects
@Composable
fun MyApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Concepts: = remember, by remember, rememberSaveable",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(16.dp))

        // Remember with =
        val counter1 = remember { mutableStateOf(0) }
        CounterButton(
            label = "Counter 1 (= remember)",
            count = counter1.value,
            increment = { counter1.value++ }
        )

        // Remember with by
        var counter2 by remember { mutableStateOf(0) }
        CounterButton(
            label = "Counter 2 (by remember)",
            count = counter2,
            increment = { counter2++ }
        )

        Spacer(Modifier.height(16.dp))

        // RememberSaveable with =
        val counter3 = rememberSaveable { mutableStateOf(0) }
        CounterButton(
            label = "Counter 3 (= rememberSaveable)",
            count = counter3.value,
            increment = { counter3.value++ }
        )

        // RememberSaveable with by
        var counter4 by rememberSaveable { mutableStateOf(0) }
        CounterButton(
            label = "Counter 4 (by rememberSaveable)",
            count = counter4,
            increment = { counter4++ }
        )
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
    MyApp()
}
