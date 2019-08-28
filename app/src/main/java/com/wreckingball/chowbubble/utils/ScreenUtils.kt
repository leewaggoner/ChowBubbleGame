package com.wreckingball.chowbubble.utils

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity

class ScreenUtils(private val context: Context, activity: AppCompatActivity) {
    var screenDims: Point

    init {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        screenDims = Point(metrics.widthPixels, metrics.heightPixels)
    }
}