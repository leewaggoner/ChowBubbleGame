package com.wreckingball.chowbubble.controllers

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.MotionEvent
import com.wreckingball.chowbubble.activities.ActivityGameOver
import com.wreckingball.chowbubble.graphics.Background
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.KoinComponent
import org.koin.core.inject

class ChowController(val context: Context) : KoinComponent {
    private val screenUtils: ScreenUtils by inject()
    private val background: Background by inject()
    private val frameBufferBitmap = Bitmap.createBitmap(screenUtils.screenDims.x, screenUtils.screenDims.y, Bitmap.Config.ARGB_8888)
    private val frameBufferCanvas = Canvas(frameBufferBitmap)

    fun startGame() {
    }

    fun onTouch(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val intent = Intent(context, ActivityGameOver::class.java)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun updateChow() {
    }

    fun drawChow(canvas: Canvas) {
        background.onDraw(frameBufferCanvas)
        canvas.drawBitmap(frameBufferBitmap, 0f, 0f, null)
    }
}