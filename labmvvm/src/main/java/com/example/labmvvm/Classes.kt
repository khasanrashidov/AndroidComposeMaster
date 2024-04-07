package com.example.labmvvm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image


class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    private val _rotationDegrees = MutableLiveData(0f)
    val rotationDegrees: LiveData<Float> = _rotationDegrees

    fun incrementCounter() {
        _count.value = (_count.value ?: 0) + 1
        _rotationDegrees.value = (_rotationDegrees.value ?: 0f) + 90f // Rotate 90 degrees on each press
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    val count by viewModel.count.observeAsState(0)
    val rotationDegrees by viewModel.rotationDegrees.observeAsState(0f)
    val imagePainter = painterResource(id = R.drawable.hongjeong)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: $count", fontSize = 24.sp)
        Image(
            painter = imagePainter,
            contentDescription = "Rotating Image",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer { rotationZ = rotationDegrees },
            contentScale = ContentScale.Fit
        )
        Button(onClick = { viewModel.incrementCounter() }) {
            Text(text = "Increment")
        }
    }
}