package com.wreckingball.chowbubble.strategies

import android.graphics.Point
import com.wreckingball.chowbubble.graphics.Sprite

class SpriteUpdateDescendLeft(screenDims: Point) : SpriteUpdate {
    private var yOffset = 0f
    private var yIncrement = screenDims.y.toFloat() * 0.00001875f
    private val xIncrement = screenDims.x.toFloat() * 0.00416667f
    private var alpha = 255

    override fun onUpdate(sprite: Sprite) {
        yOffset += yIncrement
        sprite.moveX(-xIncrement)
        sprite.moveY(yOffset)

        alpha -= 1
        if (alpha < 0) {
            alpha = 0
        }
        sprite.paint.alpha = alpha
    }
}