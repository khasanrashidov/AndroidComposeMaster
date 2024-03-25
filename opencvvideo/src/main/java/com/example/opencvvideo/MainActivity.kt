package com.example.opencvvideo

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
import com.example.opencvvideo.R
import com.example.opencvvideo.ui.theme.ComposeMasterTheme
import android.net.Uri


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

    val videoUri = Uri.parse("android.resource://${context.packageName}/" + R.raw.toystory)

    LaunchedEffect(videoUri) {
        viewModel.processVideoFrame(context, videoUri)
    }

    imageBitmap?.let {
        Image(bitmap = it.asImageBitmap(), contentDescription = "Processed Video Frame")
    }
}


