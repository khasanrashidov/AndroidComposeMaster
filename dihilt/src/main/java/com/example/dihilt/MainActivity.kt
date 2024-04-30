package com.example.dihilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dihilt.ui.theme.ComposeMasterTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * # Hilt: Dependency Injection Simplified
 * Hilt is a dependency injection library for Android that simplifies manual dependency injection in your project.
 * It is built on top of the popular DI framework Dagger and provides a standard way to incorporate Dagger dependency injection
 * into an Android application in a simpler and more cohesive way.
 *
 * ## Key Concepts of Hilt
 *
 * - **Automatic Component Management**: Hilt manages components' lifecycles automatically. It creates, stores,
 *   and provides dependencies automatically at the correct time in the lifecycle of an Android app.
 *
 * - **Simplified Configuration**: Hilt uses predefined components that match Android's lifecycle
 *   (such as Application, Activity, Fragment, View, Service, etc.), which significantly reduces the setup
 *   and configuration effort that Dagger usually requires.
 *
 * - **Compile-time Validation**: Like Dagger, Hilt performs most of its analysis at compile-time, ensuring
 *   that dependency injection errors are caught early in the development process, which reduces runtime issues
 *   and crashes due to misconfigured dependencies.
 *
 * - **Standardized Components**: Hilt defines standard components and scopes aligned with Android lifecycles,
 *   reducing the chances of making mistakes related to the lifecycle and scope of the provided dependencies.
 *
 * ## How Hilt Works
 *
 * To understand how Hilt works, it’s essential to look at its main components:
 *
 * - **@HiltAndroidApp**: Placed on the Application class, this annotation initializes Hilt's code generation,
 *   including a base class for your application that can serve dependencies.
 *
 * - **@AndroidEntryPoint**: This annotation can be added to Android classes like activities, fragments, views, etc.
 *   It makes Dagger generate a corresponding Hilt component under the hood for each class, allowing these classes
 *   to receive dependencies.
 *
 * - **@Inject**: Used to request dependencies. It can be placed on constructor, field, or method in Android
 *   classes annotated with `@AndroidEntryPoint`.
 *
 * - **Modules and Installer Modules**: Regular Dagger modules are used to define how to provide instances of
 *   different types. Hilt modules are abstract classes annotated with `@Module`. Additionally, `@InstallIn`
 *   is used to specify which Hilt component(s) the module will provide bindings for.
 *
 * - **Component Scopes**: Hilt utilizes predefined scopes like `@Singleton`, `@ActivityRetainedScoped`,
 *   `@ActivityScoped`, etc., to control the lifespan of dependencies.
 *
 * ## The Flow of Dependency Injection with Hilt
 *
 * 1. **Initialization**: When an Android application starts, the `@HiltAndroidApp` annotated application
 *    class triggers the generation of the DI graph, preparing all singleton scoped dependencies.
 *
 * 2. **Injection into Entry Points**: For each Android class annotated with `@AndroidEntryPoint`, Hilt
 *    automatically generates a subcomponent that can inject the required dependencies according to the annotations
 *    like `@Inject`.
 *
 * 3. **Provision and Usage**: During the component's (like an Activity or Fragment) lifecycle, the required
 *    dependencies are provided by Hilt's managed components. This is typically done without the user having
 *    to manually control the creation or lifecycle of these objects.
 *
 * 4. **Scope Management**: As Android components are created and destroyed, Hilt manages scoped dependencies
 *    (like those scoped to an Activity) ensuring they're only available while the component exists.
 *
 * ## Benefits of Using Hilt
 *
 * - **Reduced Boilerplate**: Automatically handles component lifecycles and generation.
 * - **Improved Readability**: By standardizing how components are injected, the codebase becomes easier to understand.
 * - **Enhanced Maintainability**: Easier to manage and update dependencies in a large team or on large projects.
 * - **Robustness**: Compile-time validations reduce runtime errors significantly.
 *
 * By abstracting much of the complexity of setting up Dagger, Hilt makes dependency injection easier to implement correctly
 * and reduces the likelihood of common mistakes related to Dagger's manual setup and configuration.
 */

@AndroidEntryPoint
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
                    GreetingScreen()
                }
            }
        }
    }
}

@Composable
fun GreetingScreen() {
    val viewModel: GreetingViewModel = viewModel()
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.updateGreeting(text) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Update Greeting")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = viewModel.message.value, style = MaterialTheme.typography.headlineMedium)
    }
}
