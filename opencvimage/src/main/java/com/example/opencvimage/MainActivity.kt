package com.example.opencvimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.opencvimage.R
import com.example.opencvimage.ui.theme.ComposeMasterTheme
import com.example.opencvinput.R


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
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp(viewModel: ImageViewModel = viewModel()) {
    val context = LocalContext.current
    val imageBitmap by viewModel.imageBitmap.observeAsState()

    LaunchedEffect(true) {
        viewModel.loadImage(context, R.drawable.hongjeong)
    }

    imageBitmap?.let {
        Image(bitmap = it.asImageBitmap(), contentDescription = "Hong Jeong Image")
    }
}


