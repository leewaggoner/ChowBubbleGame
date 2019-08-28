package com.wreckingball.chowbubble.strategies

import android.graphics.Canvas
import com.wreckingball.chowbubble.graphics.Sprite

class SpriteDrawStatic : SpriteDraw {
    override fun onDraw(canvas: Canvas, sprite: Sprite) {
        canvas.drawBitmap(sprite.getBitmap(), sprite.x, sprite.y, sprite.paint)
    }
}