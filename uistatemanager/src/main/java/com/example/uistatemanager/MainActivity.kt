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
 * | Data Holder  | Compose Adapter | Lifecycle-Aware | Hot/Cold Stream | Multiple Collectors | Buffering | Suspendable | Description |
 * |--------------|-----------------|-----------------|-----------------|---------------------|-----------|-------------|-------------|
 * | `LiveData`   | `observeAsState`| Yes             | Cold            | No                  | No        | No          | `LiveData` is lifecycle-aware, and `observeAsState` allows Compose to react to `LiveData` updates, maintaining lifecycle awareness. |
 * | `Flow`       | `collectAsState`| No              | Cold            | Yes                 | No        | Yes         | `Flow` emits multiple values sequentially, and `collectAsState` collects these emissions in Compose to update the UI dynamically. |
 * | `StateFlow`  | `collectAsState`| No              | Hot             | Yes                 | No        | Yes         | `StateFlow` is a state-holder observable flow, and `collectAsState` is used in Compose to collect its state emissions, updating the UI reactively. |
 * | `SharedFlow` | `collectAsState`| No              | Hot             | Yes                 | Yes       | Yes         | `SharedFlow` is a hot flow that emits values to multiple collectors, with `collectAsState` enabling Compose to react to these emissions for UI updates. `SharedFlow` also supports buffering. |
 * | `MutableState` | `remember`    | Yes             | Hot             | Yes                 | No        | No          | `MutableState` is a state holder that can be observed by Compose. It's lifecycle-aware and updates the UI when its value changes. |
 * | `MutableLiveData` | `observeAsState` | Yes       | Cold            | No                  | No        | No          | `MutableLiveData` is a `LiveData` whose value can be changed. `observeAsState` allows Compose to react to its updates. |
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

    // 1. val sharedFlowState by viewModel.sharedFlow.collectAsState("No SharedFlow messages yet...")
    // It collects the latest value emitted by the Flow and represents it as a State object.
    // This is useful when you want to observe a Flow and update the UI when the Flow emits a new value.
    // 2. all values emitted by the SharedFlow are collected, even when the composable is not active
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
