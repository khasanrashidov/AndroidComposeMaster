package com.example.camerax2

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.*
import androidx.camera.core.ImageProxy

class RenderscriptProcessor(private val context: Context) {

    private val renderScript = RenderScript.create(context)
    private val yuvToRgbIntrinsic = ScriptIntrinsicYuvToRGB.create(renderScript, Element.U8_4(renderScript))

    fun processImage(image: ImageProxy): Bitmap {
        val yBuffer = image.planes[0].buffer
        val uBuffer = image.planes[1].buffer
        val vBuffer = image.planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuvTypeBuilder = Type.Builder(renderScript, Element.U8(renderScript)).setX(nv21.size)
        val rgbaTypeBuilder = Type.Builder(renderScript, Element.RGBA_8888(renderScript)).setX(image.width).setY(image.height)

        val yuvType = yuvTypeBuilder.create()
        val rgbaType = rgbaTypeBuilder.create()

        val inputAllocation = Allocation.createTyped(renderScript, yuvType, Allocation.USAGE_SCRIPT)
        val outputAllocation = Allocation.createTyped(renderScript, rgbaType, Allocation.USAGE_SCRIPT)

        inputAllocation.copyFrom(nv21)

        yuvToRgbIntrinsic.setInput(inputAllocation)
        yuvToRgbIntrinsic.forEach(outputAllocation)

        val bitmap = Bitmap.createBitmap(image.width, image.height, Bitmap.Config.ARGB_8888)
        outputAllocation.copyTo(bitmap)

        image.close()

        return bitmap
    }
}
