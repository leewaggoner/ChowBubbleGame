package com.wreckingball.chowbubble.strategies

import android.graphics.Point
import com.wreckingball.chowbubble.graphics.Sprite

class SpriteUpdateUpDown(screenDims: Point) : SpriteUpdate {
    private val RANGE = 20
    private var moveY = screenDims.y.toFloat() * 0.000125f
    private var baseY = -1.0f

    override fun onUpdate(sprite: Sprite) {
        val currentY = sprite.y
        if (baseY < 0f) {
            baseY = currentY
        }

        if (currentY > baseY + RANGE || currentY < baseY - RANGE) {
            moveY = -moveY
        }

        sprite.moveY(moveY)
    }
}