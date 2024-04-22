package com.example.testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testing.ui.theme.ComposeMasterTheme

/**
 * Summary of Android Development Practices
 *
 * | Aspect        | Description                                      | Tools/Techniques                             |
 * |---------------|--------------------------------------------------|----------------------------------------------|
 * | **Testing**   | Ensure the application behaves as expected.      |                                              |
 * | Unit Testing  | Test individual components in isolation.         | JUnit, Mockito, Robolectric                  |
 * | Integration Testing | Test interactions between app components.  | Espresso, AndroidX Test Orchestrator         |
 * | UI Testing    | Simulate user interactions with the UI.          | Espresso, UI Automator                       |
 * | Performance Testing | Test app performance under stress.         | Android Profiler, Monkey tool                |
 * |---------------|--------------------------------------------------|----------------------------------------------|
 * | **Debugging** | Identify and resolve issues causing app errors. |                                              |
 * | Logcat        | Capture system output and app logging.           | Android Studio Logcat                        |
 * | Debugger      | Step through code, inspect variables.            | Android Studio Debugger                      |
 * | Lint          | Detect bugs, optimizations, standard violations. | Android Studio Lint                          |
 * |---------------|--------------------------------------------------|----------------------------------------------|
 * | **Profiling** | Understand and optimize resource usage.          |                                              |
 * | Android Profiler | Provide real-time data on CPU, memory, network. | Android Studio Profiler                    |
 * | Memory Profiler  | Identify memory leaks, monitor allocations.   | Memory Profiler in Android Studio            |
 * | CPU Profiler  | Analyze thread performance, identify bottlenecks. | CPU Profiler in Android Studio              |
 * | Network Profiler | Monitor network data transfer.                | Network Profiler in Android Studio           |
 *
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
                    CounterScreen()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CounterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val counter = remember { mutableStateOf(0) }

        Text(text = "Counter: ${counter.value}")

        Button(
            onClick = { counter.value++ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Increase Counter")
        }
    }
}

