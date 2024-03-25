package com.example.opencvcamera

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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.opencvvideo.R
import com.example.opencvcamera.ui.theme.ComposeMasterTheme
import android.net.Uri
import org.opencv.android.CameraBridgeViewBase

class MainActivity : ComponentActivity(), ImageViewModel.CameraController {
    private lateinit var cameraView: CameraBridgeViewBase

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

    override fun startCameraView() {
        cameraView.enableView()
    }

    override fun stopCameraView() {
        cameraView.disableView()
    }
}

@Composable
fun MyApp(viewModel: ImageViewModel = viewModel()) {
    val context = LocalContext.current
    val imageBitmap by viewModel.imageBitmap.observeAsState()

    val videoUri = Uri.parse("android.resource://${context.packageName}/" + R.raw.toystory)

    LaunchedEffect(videoUri) {
        viewModel.chooseInputSource(context, "camera", videoUri) // use "camera" to start camera view
    }

    imageBitmap?.let {
        Image(bitmap = it.asImageBitmap(), contentDescription = "Processed Video Frame")
    }

}