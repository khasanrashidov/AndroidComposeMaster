package com.example.diprinciple

/**
 * # Properties of DI
 *
 * Dependency Injection (DI) is a design pattern used to implement IoC (Inversion of Control),
 * allowing programs to follow the Dependency Inversion Principle. The main reasons for using DI include:
 *
 * - **Reusability**: Enhances the reusability of code. Components can be reused in different contexts
 *   by injecting dependencies that conform to the expected interfaces, rather than hard-coding specific implementations.
 *
 * - **Testability**: Improves testability by facilitating the injection of mock implementations during testing,
 *   thus isolating the unit tests from external systems or complex dependencies.
 *
 * - **Refactoring**: Simplifies refactoring efforts by decoupling components from their dependencies,
 *   making changes in one part of the system less likely to affect others.
 *
 * - **Maintainability**: Increases maintainability by organizing the system into well-defined, loosely coupled,
 *   and easily understandable components, which simplifies ongoing development and troubleshooting.
 *
 * - **Scalability**: Supports better scalability by managing object lifecycles and dependencies appropriately,
 *   which is crucial for applications that need to scale in complexity and size.
 *
 * - **Flexibility**: Offers flexibility in application configuration and adaptation to changing requirements
 *   by externalizing component dependencies, enabling easier adjustments to system behavior without altering business logic.
 */

/**
 * # DI comparisons
 * | DI Method | Reusability | Testability | Refactoring | Maintainability | Scalability | Flexibility | Injection Type |
 * |-----------|-------------|-------------|-------------|-----------------|-------------|-------------|----------------|
 * | Manual Injection |             |             |             |                 |             |             |                |
 * | - Constructor    | High        | High        | High        | High            | High        | Medium      | Dynamic        |
 * | - Field          | Medium      | Low         | Medium      | Medium          | Medium      | High        | Dynamic        |
 * | - Method         | High        | High        | High        | High            | High        | High        | Dynamic        |
 * | Framework Injection |          |             |             |                 |             |             |                |
 * | - Hilt           | High        | High        | High        | High            | High        | High        | Static         |
 * | - Koin           | High        | High        | High        | High            | High        | High        | Dynamic        |
 * | - Dagger         | High        | High        | High        | High            | High        | High        | Static         |
 *
 * - **Constructor**: This is the most common form of dependency injection. The dependencies of a class are provided through its constructor.
 * - **Field**: In this method, the dependencies are directly inserted into the fields of a class. This is often used in conjunction with a dependency injection framework that can automatically inject the dependencies.
 * - **Method**: In this method, the dependencies are provided through a method. This is often used when the dependency is not needed throughout the object's lifetime but only for specific operations.
 * - **Hilt**: Hilt is a dependency injection library built on top of Dagger to simplify the setup process. It uses annotations to automatically set up dependencies and inject them where needed. Hilt performs static injection.
 * - **Koin**: Koin is a lightweight dependency injection library that doesn't rely on annotations. It uses a DSL for configuration and is easy to set up and use. Koin performs dynamic injection.
 * - **Dagger**: Dagger is a fully static, compile-time dependency injection framework that uses annotations to define and create dependencies. Dagger performs static injection.
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diprinciple.ui.theme.ComposeMasterTheme

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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMasterTheme {
        Greeting("Android")
    }
}