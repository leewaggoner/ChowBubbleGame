package com.wreckingball.chowbubble.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF

fun scaleBitmap(bitmap: Bitmap, scale: Float): Bitmap {
    val sourceWidth = bitmap.width
    val sourceHeight = bitmap.height

    val scaledWidth = (scale * sourceWidth).toInt()
    val scaledHeight = (scale * sourceHeight).toInt()

    val targetRect = RectF(0f, 0f, scaledWidth.toFloat(), scaledHeight.toFloat())

    val result = Bitmap.createBitmap(scaledWidth, scaledHeight, bitmap.config)
    val canvas = Canvas(result)
    canvas.drawBitmap(bitmap, null, targetRect, null)

    bitmap.recycle()

    return result
}
