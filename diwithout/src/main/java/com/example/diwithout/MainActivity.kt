package com.example.diwithout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.diwithout.ui.theme.DependencyInjectionTheme

/**
 * # Dependency Injection
 * [Android Developer](https://developer.android.com/training/dependency-injection/hilt-android) | [Wikipedia](https://en.wikipedia.org/wiki/Dependency_injection)
 *
 * | Method                | Ease of Use | Testability | Flexibility | Description |
 * |-----------------------|-------------|-------------|-------------|-------------|
 * | Constructor Injection | High        | High        | Medium      | This is the most common form of dependency injection. The dependencies of a class are provided through its constructor. |
 * | Setter Injection      | Medium      | Medium      | High        | In this method, the dependencies are provided through setter methods (or property setters in Kotlin). This is less common than constructor injection, but it can be useful when a class has optional dependencies that don't need to be provided at construction time. |
 * | Method Injection      | Low         | Medium      | High        | This is similar to setter injection, but the dependencies are provided through any method, not just setters. This can be useful when a class needs to reconfigure its dependencies at runtime. |
 * | Field Injection       | High        | Low         | Medium      | In this method, the dependencies are directly inserted into the fields of a class. This is often used in conjunction with a dependency injection framework that can automatically inject the dependencies. |
 * | Interface Injection   | Low         | High        | High        | In this method, the dependent object implements an interface which declares a setter method for the dependency. The injector uses this interface to set the dependency. |
 * | Service Locator Pattern | High      | Low         | High        | This is not a form of dependency injection, but it's a related pattern that can be used to manage dependencies. In this pattern, a service locator object is used to look up the dependencies as needed, rather than having them injected. |
 * | Hilt                  | High        | High        | High        | Hilt is a dependency injection library built on top of Dagger to simplify the setup process. It uses annotations to automatically set up dependencies and inject them where needed. |
 * | Koin                  | High        | High        | High        | Koin is a lightweight dependency injection library that doesn't rely on annotations. It uses a DSL for configuration and is easy to set up and use. |
 * | Manual Injection      | Medium      | High        | High        | Manual Injection involves manually constructing objects and their dependencies. It gives full control over how dependencies are created and injected but requires more boilerplate code and maintenance. |
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
    val greetingViewModel = GreetingViewModel()

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