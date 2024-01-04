// 2023 Prof. Hong Jeong
// Concepts: "=", "by", "remember", and  "rememberSaveable"
// 1. remember: remember is a function that returns a value that will be remembered between recompositions.
// 2. rememberSaveable: rememberSaveable is a function that returns a value that will be remembered between recompositions and saved to the instance state.
// 3. rememberSaveable is a wrapper around remember that saves the value to the instance state.
// 4. rememberSaveable is useful for saving state across process death, such as when the app is backgrounded.
// 5. "=" and "by" are differ in accessing value.
// 6. "=" is used to access the value directly.
// 7. "by" is used to access the value indirectly.

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

@Composable
fun MyApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Concepts: =, by, remember, rememberSaveable",
            style = MaterialTheme.typography.headlineLarge
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
