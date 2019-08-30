package com.wreckingball.chowbubble.strategies

import android.graphics.Canvas
import com.wreckingball.chowbubble.graphics.Sprite

class SpriteDrawAlphaFade : SpriteDraw {
    private var x = 0f
    private var y = 0f

    override fun onDraw(canvas: Canvas, sprite: Sprite) {
        if (x == 0f) {
            x = sprite.x
            y = sprite.y
        }
        canvas.drawBitmap(sprite.getBitmap(), x, y, sprite.paint)
    }
}