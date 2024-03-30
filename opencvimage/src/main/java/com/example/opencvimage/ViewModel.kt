package com.example.opencvimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import org.opencv.core.CvType

class ImageViewModel : ViewModel() {
    private val _imageBitmap = MutableLiveData<Bitmap>()
    val imageBitmap: LiveData<Bitmap> = _imageBitmap

    fun loadImage(context: Context, resourceId: Int) {
        if (!OpenCVLoader.initLocal()) {
            Log.e("OpenCV", "Unable to load OpenCV!")
            return
        }

        // Input bitmap
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        val bitmap = BitmapFactory.decodeResource(context.resources, resourceId, options)
        val mat = Mat()
        Utils.bitmapToMat(bitmap, mat)

        // Image processing
        // Simple convert
//        Imgproc.GaussianBlur(mat, mat, org.opencv.core.Size(5.0, 5.0), 0.0, 1.0)




        // Process the image with OpenCV here, e.g., convert to grayscale
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY)
        Imgproc.Canny(mat, mat, 1.0, 25.0)




        // Apply Sobel operator
//        val grad_x = Mat()
//        val grad_y = Mat()
//        val abs_grad_x = Mat()
//        val abs_grad_y = Mat()
//        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY)
//        Imgproc.Sobel(mat, grad_x, CvType.CV_16S, 1, 0)
//        Core.convertScaleAbs(grad_x, abs_grad_x)
//        Imgproc.Sobel(mat, grad_y, CvType.CV_16S, 0, 1)
//        Core.convertScaleAbs(grad_y, abs_grad_y)
//        Core.addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0.0, mat)


        // Output the result bitmap
        val resultBitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(mat, resultBitmap)
        _imageBitmap.postValue(resultBitmap)
    }
}
