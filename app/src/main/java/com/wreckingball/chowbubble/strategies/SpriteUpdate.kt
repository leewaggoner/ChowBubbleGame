package com.wreckingball.chowbubble.strategies

import com.wreckingball.chowbubble.graphics.Sprite

interface SpriteUpdate {
    fun onUpdate(sprite: Sprite)
}