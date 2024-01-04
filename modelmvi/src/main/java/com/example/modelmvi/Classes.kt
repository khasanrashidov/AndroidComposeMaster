package com.example.modelmvi

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Model
data class CounterModel(val count: Int)

// ViewModel
class CounterViewModel : ViewModel() {
    private val _state = MutableLiveData(CounterModel(0))
    val state: LiveData<CounterModel> = _state

    fun processIntent(intent: CounterIntent) {
        when (intent) {
            is CounterIntent.Increment -> {
                val newCount = _state.value?.count?.plus(1) ?: 0
                _state.value = CounterModel(newCount)
            }
        }
    }
}

// View (Jetpack Compose)
sealed class CounterIntent {
    object Increment : CounterIntent()
}

@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    val state by viewModel.state.observeAsState(CounterModel(0))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: ${state.count}", fontSize = 24.sp)
        Button(onClick = { viewModel.processIntent(CounterIntent.Increment) }) {
            Text(text = "Increment")
        }
    }
}
