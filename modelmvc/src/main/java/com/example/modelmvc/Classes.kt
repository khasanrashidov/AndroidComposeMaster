package com.example.modelmvc

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

// Model
data class CounterModel(var count: Int)

// Controller
class CounterController(private val model: CounterModel) {
    fun incrementCounter() {
        model.count++
    }
}

// View (Jetpack Compose)
@Composable
fun CounterScreen(model: CounterModel, controller: CounterController) {
    var count by remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: $count", fontSize = 24.sp)
        Button(onClick = {
            controller.incrementCounter()
            count = model.count
        }) {
            Text(text = "Increment")
        }
    }
}