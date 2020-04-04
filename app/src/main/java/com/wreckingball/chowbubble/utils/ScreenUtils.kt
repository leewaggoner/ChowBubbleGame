package com.wreckingball.chowbubble.utils

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.graphics.Sprite

class ScreenUtils(private val context: Context, activity: AppCompatActivity) {
    var screenDims: Point

    init {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        screenDims = Point(metrics.widthPixels, metrics.heightPixels)
    }

    fun pxToDp(px: Int): Int {
        return (px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun dpToPx(dp: Int): Int {
        return (dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun centerSpriteX(sprite: Sprite, screenWide: Int): Float {
        return (screenWide.toFloat() - sprite.getWidth()) / 2.0f
    }
}