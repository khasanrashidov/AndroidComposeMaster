package com.example.uistatemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uistatemanager.ui.theme.ComposeMasterTheme
import androidx.lifecycle.LiveData

/**
 * # UI state management tools
 *
 * | Data Holder  | Compose Adapter | Description |
 * |--------------|-----------------|-------------|
 * | `LiveData`   | `observeAsState`| `LiveData` is lifecycle-aware, and `observeAsState` allows Compose to react to `LiveData` updates, maintaining lifecycle awareness. |
 * | `StateFlow`  | `collectAsState`| `StateFlow` is a state-holder observable flow, and `collectAsState` is used in Compose to collect its state emissions, updating the UI reactively. |
 * | `Flow`       | `collectAsState`| `Flow` emits multiple values sequentially, and `collectAsState` collects these emissions in Compose to update the UI dynamically. |
 * | `SharedFlow` | `collectAsState`| `SharedFlow` is a hot flow that emits values to multiple collectors, with `collectAsState` enabling Compose to react to these emissions for UI updates. |
 * | `MutableState` | `remember` | `MutableState` is a mutable state holder that can be observed by Composable functions. When the state changes, the Composable that's observing this state will recompose. |
 * | `derivedStateOf` | N/A | `derivedStateOf` is used when you have a state that's derived from one or more other states. It ensures that the derived state is only recalculated when one of its dependencies changes. |
 * | `produceState` | N/A | `produceState` is used when you want to produce a state from an asynchronous operation, like a network request or a database query. |
 * | `DisposableEffect` | N/A | `DisposableEffect` is used when you need to perform side effects in a Composable function that also need cleanup when the Composable leaves the composition. |
 * | `LaunchedEffect` | N/A | `LaunchedEffect` is used when you need to perform a suspend function in a Composable function. It launches a coroutine that's scoped to the Composable's lifecycle. |
 * | `SideEffect` | N/A | `SideEffect` is used when you need to perform side effects in a Composable function that don't need cleanup when the Composable leaves the composition. |
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
                    UIStateManager()
                }
            }
        }
    }
}

@Composable
fun UIStateManager(viewModel: UIStateManagerViewModel = viewModel()) {
    // Observing LiveData
    val liveDataState by viewModel.liveData.observeAsState("Waiting for LiveData...")

    // Collecting StateFlow
    val stateFlowState by viewModel.stateFlow.collectAsState()

    // Collecting Flow - flows require a lifecycle to collect in Compose
    var flowState by remember { mutableStateOf("Waiting for Flow...") }
    LaunchedEffect(Unit) {
        viewModel.flow.collect { newValue ->
            flowState = newValue
        }
    }

    // Collecting SharedFlow - this is an event-based flow, might not have a value
    var sharedFlowState by remember { mutableStateOf("No SharedFlow messages yet...") }
    LaunchedEffect(Unit) {
        viewModel.sharedFlow.collect { newValue ->
            sharedFlowState = newValue
        }
    }

    Column {
        Button(onClick = { viewModel.updateLiveData() }) {
            Text("Update LiveData")
        }
        Text(liveDataState)

        Button(onClick = { viewModel.updateStateFlow() }) {
            Text("Update StateFlow")
        }
        Text(stateFlowState)

        Button(onClick = { viewModel.updateFlow() }) {
            Text("Trigger Flow")
        }
        Text(flowState)

        Button(onClick = { viewModel.emitToSharedFlow() }) {
            Text("Emit to SharedFlow")
        }
        Text(sharedFlowState)
    }
}

@Preview
@Composable
fun PreviewUIStateManager() {
    UIStateManager()
}
