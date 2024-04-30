package com.example.dependencyinjection2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dependencyinjection2.ui.theme.ComposeMasterTheme

/**
 *  # Compositional Dependency Injection
 * | Injection Type | Flexibility | Reusability | Testability |
 * |----------------|-------------|-------------|-------------|
 * | Manual         | Less Flexible: Dependencies have to be passed through every intermediate Composable that requires them. This can become cumbersome and difficult to manage as your application grows and the dependency graph becomes more complex. | Less Reusable: Directly passing dependencies couples the Composable functions tightly with their dependencies. This means that if you want to reuse a Composable function in a different context where the dependencies might be different, you would have to change the function's implementation or its signature. | Less Testable: When testing Composable functions that have dependencies passed directly, you need to modify the Composable functions' signature to replace the dependencies with test doubles (e.g., mocks or stubs). This can make the testing process more complicated. |
 * | Compositional  | More Flexible: CompositionLocal allows you to pass dependencies down the composition tree without explicitly passing them through each intermediate Composable. | More Reusable: Using CompositionLocal allows you to reuse Composables in different contexts without having to change their implementation. Composables will use the dependency provided by their parent Composables or the app's root Composable. | More Testable: With CompositionLocal, you can easily replace the dependencies with test doubles when testing your Composables, making it easier to isolate the Composable under test from the real implementation of its dependencies. |
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

// Now, let's create a CompositionLocal for the GreetingService:
val LocalGreetingService = staticCompositionLocalOf<GreetingService> {
    error("No GreetingService provided")
}

val LocalFarewellService = staticCompositionLocalOf<FarewellService> {
    error("No FarewellService provided")
}

// Create a Composable function to provide the dependency:
@Composable
fun GreetingServiceProvider(greetingService: GreetingService, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalGreetingService provides greetingService) {
        content()
    }
}

@Composable
fun FarewellServiceProvider(farewellService: FarewellService, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalFarewellService provides farewellService) {
        content()
    }
}

// Create a Composable function that uses the dependency:
@Composable
fun GreetUser(name: String) {
    val greetingService = LocalGreetingService.current
    val greeting = remember(name) { greetingService.greet(name) }
//    val farewell = remember(name) { greetingService.farewell(name) }
    Text(text = greeting)
//    Text(text = farewell)
}

@Composable
fun FarewellUser(name: String) {
    val farewellService = LocalFarewellService.current
    val farewell = remember(name) { farewellService.farewell(name) }
    Text(text = farewell)
}

// Finally, let's use the provided dependency in the main Composable:
@Composable
fun MyApp() {
    val greetingService = remember { GreetingServiceImpl() }
    val farewellService = remember { FarewellServiceImpl() }
    Column (modifier=Modifier.padding(16.dp)){
        GreetingServiceProvider(greetingService = greetingService) {
            Column {
                GreetUser(name = "John")
                GreetUser(name = "Jane")

            }
        }

        FarewellServiceProvider(farewellService = farewellService) {
            Column {
                FarewellUser(name = "John")
                FarewellUser(name = "Jane")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp()
}

