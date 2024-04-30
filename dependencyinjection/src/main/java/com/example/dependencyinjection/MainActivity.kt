package com.example.dependencyinjection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dependencyinjection.ui.theme.ComposeMasterTheme

/**
 *  # Manual injection
 * Flexibility, Reusability, and Testability in Dependency Injection:
 *
 * - **Less Flexible**: When you pass dependencies directly, you have to pass them through every intermediate Composable that requires them. This can become cumbersome and difficult to manage as your application grows and the dependency graph becomes more complex. In contrast, using CompositionLocal allows you to pass dependencies down the composition tree without explicitly passing them through each intermediate Composable.
 *
 * - **Less Reusable**: Directly passing dependencies couples the Composable functions tightly with their dependencies. This means that if you want to reuse a Composable function in a different context where the dependencies might be different, you would have to change the function's implementation or its signature. On the other hand, using CompositionLocal allows you to reuse Composables in different contexts without having to change their implementation. Composables will use the dependency provided by their parent Composables or the app's root Composable.
 *
 * - **Less Testable**: When you're testing Composable functions that have dependencies passed directly, you need to modify the Composable functions' signature to replace the dependencies with test doubles (e.g., mocks or stubs). This can make the testing process more complicated. With CompositionLocal, you can easily replace the dependencies with test doubles when testing your Composables, making it easier to isolate the Composable under test from the real implementation of its dependencies.
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
                    MyAppDirect()
                }
            }
        }
    }
}

@Composable
fun GreetUserDirect(greetingService: GreetingService, name: String) {
    val greeting = remember(name) { greetingService.greet(name) }
    Text(text = greeting)
}

@Composable
fun MyAppDirect() {
    val greetingService = remember { GreetingServiceImpl() }

    Column {
        GreetUserDirect(greetingService = greetingService, name = "John")
        GreetUserDirect(greetingService = greetingService, name = "Jane")
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppDirectPreview() {
    MyAppDirect()
}
