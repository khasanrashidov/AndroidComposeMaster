package com.example.modelmvp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

// Model
data class CounterModel(var count: Int)

// Presenter
class CounterPresenter(internal val model: CounterModel) {
    fun incrementCounter() {
        model.count++
    }
}

// View (Jetpack Compose)
interface CounterView {
    fun updateCount(count: Int)
}

@Composable
fun CounterScreen(presenter: CounterPresenter) {
    val count = remember { mutableStateOf(0) }
    val view = object : CounterView {
        override fun updateCount(newCount: Int) {
            count.value = newCount
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: ${count.value}", fontSize = 24.sp)
        Button(onClick = {
            presenter.incrementCounter()
            view.updateCount(presenter.model.count)
        }) {
            Text(text = "Increment")
        }
    }
}
