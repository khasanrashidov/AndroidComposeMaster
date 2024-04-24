package com.example.uistatemanager1

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
import com.example.uistatemanager1.ui.theme.ComposeMasterTheme
import androidx.lifecycle.LiveData


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
    //1. Observing LiveData
    val liveDataState by viewModel.liveData.observeAsState("Waiting for LiveData...")

    //2. Collecting StateFlow
    val stateFlowState by viewModel.stateFlow.collectAsState()

    //3. Collecting SharedFlow for events
    var lastEvent by remember { mutableStateOf("No events yet...") }
    LaunchedEffect(viewModel) {
        viewModel.eventFlow.collect { event ->
            lastEvent = event
        }
    }

    //4. Collecting a regular Flow
    var flowValue by remember { mutableStateOf("Starting...") }
    LaunchedEffect(viewModel) {
        viewModel.numberFlow.collect { number ->
            flowValue = "Number $number"
        }
    }

    Column {
        Button(onClick = { viewModel.updateLiveData() }) {
            Text("1. Update LiveData")
        }
        Text(liveDataState)

        Button(onClick = { viewModel.updateStateFlow() }) {
            Text("2. Update StateFlow")
        }
        Text(stateFlowState)

        Button(onClick = { viewModel.emitEvent() }) {
            Text("3. Emit Event")
        }
        Text("Last event: $lastEvent")

        Text(flowValue)
    }
}

@Preview
@Composable
fun PreviewUIStateManager() {
    UIStateManager()
}