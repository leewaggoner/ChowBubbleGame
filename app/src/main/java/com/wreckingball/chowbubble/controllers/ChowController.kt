package com.wreckingball.chowbubble.controllers

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import com.wreckingball.chowbubble.activities.ActivityGameOver

class ChowController(val context: Context) {

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

    fun drawChow(canvas: Canvas?) {
        canvas?.drawColor(Color.BLUE)
    }
}