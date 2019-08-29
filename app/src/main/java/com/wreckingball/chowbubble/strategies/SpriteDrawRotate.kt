package com.wreckingball.chowbubble.strategies

import android.graphics.Canvas
import com.wreckingball.chowbubble.graphics.Sprite

class SpriteDrawRotate : SpriteDraw {
    override fun onDraw(canvas: Canvas, sprite: Sprite) {
        val matrix = sprite.matrix
        if (matrix != null) {
            canvas.drawBitmap(sprite.getBitmap(), matrix, null)
        }
    }
}