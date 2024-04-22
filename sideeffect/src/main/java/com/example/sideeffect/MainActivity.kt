package com.example.sideeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sideeffect.ui.theme.ComposeMasterTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.widget.Toast

/**
 * Summary of Jetpack Compose side effect APIs and their uses.
 *
 * | API                  | Description |
 * |----------------------|-------------|
 * | `LaunchedEffect`     | Used to launch side effects (like coroutines) that should run when specific keys change or when a Composable enters the composition. It automatically cancels the coroutine when the Composable leaves the composition or the keys change. |
 * | `rememberUpdatedState` | Remembers the latest value passed to it and provides a stable reference that always points to the latest value. Useful when you have a callback that needs to access the most current state. |
 * | `DisposableEffect`   | Used for managing cleanup logic for side effects within a Composable. It runs its cleanup logic when the Composable leaves the composition or when the specified keys change. |
 * | `produceState`       | Used to produce state in a Composable based on asynchronous operations or from a Flow. It's useful for managing state that depends on external data sources. |
 * | `CoroutineScope`     | Not specifically a Compose API but often used within Compose to launch coroutines tied to the lifecycle of a Composable. |
 * | `rememberCoroutineScope` | Creates and remembers a CoroutineScope tied to the lifecycle of the Composable. This scope is cancelled when the Composable is removed from the composition. Ideal for launching tasks that should be cancelled with the UI. |
 * | `derivedStateOf`     | Allows creating a derived state that automatically updates when any of the states it depends on change. This helps in minimizing recompositions by only updating when necessary. |
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
                    SideEffectDemo()
                }
            }
        }
    }
}



@Composable
fun SideEffectDemo() {
    Column(modifier = Modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        val context = LocalContext.current

        //1. LaunchedEffect to perform an action on button press
        var launchedEffectCount by remember { mutableStateOf(0) }
        LaunchedEffect(key1=count) {
            launchedEffectCount += 1
            Toast.makeText(context, "[LaunchedEffect] Count updated to $count", Toast.LENGTH_SHORT).show()
        }

        //2. rememberUpdatedState to keep the latest state
        val currentCount by rememberUpdatedState(count)

        //3. DisposableEffect for cleanup demonstration
        var resourceStatus by remember { mutableStateOf("No resource allocated") }
        var toggleEffect by remember { mutableStateOf(true) }
        if (toggleEffect) {
            DisposableEffect(key1 = context) {
                resourceStatus = "Resource allocated"
                onDispose {
                    resourceStatus = "Resource freed"
                }
            }
        }


        //4. produceState to produce state asynchronously
        val asyncData = produceState(initialValue = "Loading...", producer = {
            delay(10000) // simulate network delay
            value = "Data loaded"
        })

        // rememberCoroutineScope for a scope that can launch jobs
        val scope = rememberCoroutineScope()

        //5. derivedStateOf to compute a value based on other states
        val isEven = remember(count) {
            derivedStateOf { count % 2 == 0 }
        }

        // Buttons to interact with the side effects
        Button(onClick = { count += 1 }) {
            Text("Increment Counter")
        }
        Text("Counter: $count (Updated State: $currentCount)")

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            scope.launch {
                delay(500)
                count += 1
            }
        }) {
            Text("Increment in CoroutineScope")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = { toggleEffect = !toggleEffect }) {
            Text("Toggle Effect")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("[LaunchedEffect] triggered update: $launchedEffectCount")
        Text("[Disposable Effect] Resource Status: $resourceStatus")
        Text("[produceState] Async data: ${asyncData.value}")
        Text("[deriveState] Is the count even? ${isEven.value}")

        Spacer(Modifier.height(8.dp))
    }
}

