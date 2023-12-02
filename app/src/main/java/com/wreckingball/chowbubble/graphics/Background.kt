package com.wreckingball.chowbubble.graphics

import android.graphics.Bitmap
import android.graphics.Canvas
import com.wreckingball.chowbubble.R
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Background : KoinComponent {
    private val spriteBitmapFactory: SpriteBitmapFactory by inject()
    private val background: Bitmap? = spriteBitmapFactory.getBitmap(R.drawable.game_background)

    fun onDraw(canvas: Canvas) {
        background?.let {
            canvas.drawBitmap(background, 0f, 0f, null)
        }
    }
}