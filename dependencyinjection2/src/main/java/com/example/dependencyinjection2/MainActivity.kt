// Flexibility: CompositionLocal allows you to pass dependencies down the composition tree without explicitly passing them through each intermediate Composable. This is particularly useful when you have multiple layers of composables and need to access the same dependency in deeply nested composables.
//
//Reusability: By providing dependencies via CompositionLocal, you can reuse composables in different contexts without having to change their implementation. Composables will use the dependency provided by their parent composables or the app's root composable.
//
//Testability: By using CompositionLocal, you can easily replace the dependencies with test doubles (e.g., mocks or stubs) when testing your composables. This enables you to isolate the composable under test from the real implementation of its dependencies, making it easier to test the composable's behavior.

// By creating a CompositionLocal for the UserRepository and a Composable function to provide the dependency, you allow your Composables to access the UserRepository more easily and flexibly. The UserRepositoryProvider function simplifies the process of providing dependencies to your composables, and it helps to maintain a clean and organized code structure.

// In this example, we've created a GreetingService interface and its implementation (GreetingServiceImpl). Then, we've defined a CompositionLocal for the GreetingService and a Composable function (GreetingServiceProvider) to provide the dependency. We've also created a Composable function (GreetUser) that uses the provided dependency, and finally, we've used the GreetingServiceProvider to provide the dependency in our main Composable (MyApp).
//
//This approach allows us to easily manage dependencies and makes the code more flexible, reusable, and testable.

package com.example.dependencyinjection2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dependencyinjection2.ui.theme.ComposeMasterTheme

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

// Create a Composable function to provide the dependency:
@Composable
fun GreetingServiceProvider(greetingService: GreetingService, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalGreetingService provides greetingService) {
        content()
    }
}

// Create a Composable function that uses the dependency:
@Composable
fun GreetUser(name: String) {
    val greetingService = LocalGreetingService.current
    val greeting = remember(name) { greetingService.greet(name) }
    Text(text = greeting)
}

// Finally, let's use the provided dependency in the main Composable:
@Composable
fun MyApp() {
    val greetingService = remember { GreetingServiceImpl() }

    GreetingServiceProvider(greetingService = greetingService) {
        Column {
            GreetUser(name = "John")
            GreetUser(name = "Jane")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp()
}

