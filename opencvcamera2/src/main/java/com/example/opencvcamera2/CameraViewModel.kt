package com.example.opencvcamera2

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class CameraViewModel : ViewModel() {
    private val _processedImage = MutableLiveData<Bitmap>()
    val processedImage: LiveData<Bitmap> = _processedImage

    init {
        // Initialize OpenCV on ViewModel initialization.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    }

    fun processFrame(frame: Mat) {
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2GRAY) // Convert to grayscale
        val bitmap = Bitmap.createBitmap(frame.cols(), frame.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(frame, bitmap)
        _processedImage.postValue(bitmap) // Update the LiveData with the processed image
    }
}
