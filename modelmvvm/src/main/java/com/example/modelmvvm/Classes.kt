package com.example.modelmvvm

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
data class CounterModel(var count: Int)

// ViewModel
class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    fun incrementCounter() {
        _count.value = (_count.value ?: 0) + 1
    }
}

// View (Jetpack Compose)
@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    val count by viewModel.count.observeAsState(0)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: $count", fontSize = 24.sp)
        Button(onClick = { viewModel.incrementCounter() }) {
            Text(text = "Increment")
        }
    }
}