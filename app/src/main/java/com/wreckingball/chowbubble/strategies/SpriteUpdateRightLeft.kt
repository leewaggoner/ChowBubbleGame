package com.wreckingball.chowbubble.strategies

import android.graphics.Point
import com.wreckingball.chowbubble.graphics.Sprite

class SpriteUpdateRightLeft(screenDims: Point) : SpriteUpdate {
    private val RANGE = 10
    private var moveX = screenDims.x.toFloat() * 0.0002083f
    private var baseX = 0.0f

    override fun onUpdate(sprite: Sprite) {
        val currentX = sprite.x
        if (baseX == 0.0f) {
            baseX = currentX
        }

        if (currentX > baseX + RANGE || currentX < baseX - RANGE) {
            moveX = -moveX
        }

        sprite.moveX(moveX)
    }
}