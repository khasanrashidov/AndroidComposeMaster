package com.example.opencvvideo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class ImageViewModel : ViewModel() {
    private val _imageBitmap = MutableLiveData<Bitmap>()
    val imageBitmap: LiveData<Bitmap> = _imageBitmap

    fun loadImage(context: Context, resourceId: Int) {
        if (!OpenCVLoader.initDebug()) {
            Log.e("OpenCV", "Unable to load OpenCV!")
            return
        }

        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        val bitmap = BitmapFactory.decodeResource(context.resources, resourceId, options)

        val mat = Mat()
        Utils.bitmapToMat(bitmap, mat)

        // Process the image with OpenCV here, e.g., convert to grayscale

        val resultBitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(mat, resultBitmap)
        _imageBitmap.postValue(resultBitmap)
    }

    fun processVideoFrame(context: Context, videoUri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!OpenCVLoader.initLocal()) {
                Log.e("OpenCV", "Unable to load OpenCV!")
                return@launch
            }

            val mediaMetadataRetriever = MediaMetadataRetriever()
            try {
                mediaMetadataRetriever.setDataSource(context, videoUri)
                val videoLengthInUs = mediaMetadataRetriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_DURATION
                )!!.toLong() * 1000 // convert to microseconds

                for (time in 0 until videoLengthInUs step 10000000L) { // adjust step based on your needs
                    val bitmap = mediaMetadataRetriever.getFrameAtTime(
                        time,
                        MediaMetadataRetriever.OPTION_CLOSEST
                    )
                    bitmap?.let {
                        val mat = Mat()
                        Utils.bitmapToMat(it, mat)

                        // Apply your OpenCV processing here, e.g., convert to grayscale
//                        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY)
//                        Imgproc.GaussianBlur(mat, mat, org.opencv.core.Size(5.0, 5.0), 0.0, 1.0)
//                        Imgproc.Canny(mat, mat, 50.0, 150.0)



                        val processedBitmap =
                            Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888)
                        Utils.matToBitmap(mat, processedBitmap)
                        _imageBitmap.postValue(processedBitmap) // Post the processed frame
                    }
                    delay(1000) // wait for 1 second before processing the next frame
                }
            } catch (e: Exception) {
                Log.e("VideoProcessing", "Error processing video frame", e)
            } finally {
                mediaMetadataRetriever.release()
            }
        }
    }
}
