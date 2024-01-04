//While this approach is simpler, it has some drawbacks compared to using CompositionLocal:
//
//It's less flexible, as you have to pass the dependency through every intermediate Composable that requires it.
//It's less reusable, as the Composable functions are tightly coupled with their dependencies, making it more challenging to reuse them in different contexts.
//It's less testable, as you need to modify the Composable functions' signature to replace the dependencies with test doubles (e.g., mocks or stubs).

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
