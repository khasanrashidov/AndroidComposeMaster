package com.example.dimanual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.dimanual.ui.theme.DependencyInjectionTheme

/**
 * Comparison of Dependency Injection Methods:
 *
 * | Method             | Complexity | Setup Required    | Performance | Use Cases                               |
 * |--------------------|------------|-------------------|-------------|-----------------------------------------|
 * | Manual Injection   | Low        | None              | Fast        | Small projects or learning purposes.    |
 * |                    |            |                   |             | Direct control over object creation.    |
 * | Constructor Injection | Low to Medium | Minimal     | Fast        | Medium projects. Encourages immutability|
 * |                    |            |                   |             | and easier testing. Simpler than DI frameworks.|
 * | Hilt               | High       | Complex           | Fast        | Large and complex Android applications. |
 * |                    |            | Annotation-driven |             | Requires compile-time validation.       |
 * |                    |            | with Gradle setup |             | Integrates deeply with Android lifecycle.|
 * | Koin               | Medium     | Moderate          | Slightly slower | Projects preferring pure Kotlin.     |
 * |                    |            | DSL-based setup   | at runtime  | Lightweight setup with runtime DI.      |
 * |                    |            |                   |             | Simplified testing and configuration.   |
 *
 * ## Key:
 * - **Complexity**: Reflects how difficult it is to learn, implement, and maintain.
 * - **Setup Required**: Indicates the amount of configuration and setup overhead involved.
 * - **Performance**: Relates to runtime efficiency and impact on app performance.
 * - **Use Cases**: Suggests ideal scenarios for each method based on project size and complexity.
 *
 * ## Details:
 * - **Manual Injection**: Involves manually creating each class and its dependencies. It offers
 *   precise control but increases complexity and maintenance as the project grows.
 * - **Constructor Injection**: Relies on passing dependencies directly into the constructor of
 *   the class that needs them. It's simpler than using a DI framework but can still be cumbersome
 *   for very large projects.
 * - **Hilt**: Built on top of Dagger and optimized for Android. It provides robust dependency
 *   management tailored to Android's architecture but requires learning Dagger's principles.
 * - **Koin**: A lightweight DI framework that uses Kotlin's features for a clean and easy-to-use
 *   API. It handles DI at runtime which may introduce slight performance overhead compared to compile-time frameworks.
 */


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DependencyInjectionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingScreen()
                }
            }
        }
    }
}


@Composable
fun GreetingScreen() {
    // Constructor injection
    // GreetingScreen composable function injects dependencies into GreetingViewModel
    // Create an instance of GreetingRepository
    val greetingRepository = GreetingRepository()
    // Create an instance of GreetingViewModel depending on GreetingRepository
    val greetingViewModel = GreetingViewModel(greetingRepository)

    var inputName by remember { mutableStateOf("") }
    val greeting by greetingViewModel.greeting.observeAsState()

    Column {
        TextField(
            value = inputName,
            onValueChange = { inputName = it },
            label = { Text("Enter your name") }
        )
        Button(onClick = { greetingViewModel.updateGreeting(inputName) }) {
            Text("Submit")
        }


        greeting?.let { Text(text = it) }
    }
}
