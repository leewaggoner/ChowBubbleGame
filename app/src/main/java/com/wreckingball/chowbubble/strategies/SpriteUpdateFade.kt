package com.wreckingball.chowbubble.strategies

import com.wreckingball.chowbubble.graphics.Sprite

class SpriteUpdateFade : SpriteUpdate {
    private var alpha = 255
    override fun onUpdate(sprite: Sprite) {
        alpha -= 2
        if (alpha < 0) {
            alpha = 0
        }
        sprite.paint.alpha = alpha
    }
}