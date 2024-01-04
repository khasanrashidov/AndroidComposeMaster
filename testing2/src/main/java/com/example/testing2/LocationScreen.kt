package com.example.testing2


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun LocationScreen(locationViewModel: LocationViewModel = viewModel()) {
    val location: String by locationViewModel.location.collectAsState("")
    val scope = rememberCoroutineScope()

    Column {
        Text("Location: $location")
        Button(onClick = {
            scope.launch {
                locationViewModel.fetchLocation()
            }
        }) {
            Text("Get Location")
        }
    }
}
