package com.wreckingball.chowbubble.strategies

import android.graphics.Canvas
import com.wreckingball.chowbubble.graphics.Sprite

interface SpriteDraw {
    fun onDraw(canvas: Canvas, sprite: Sprite)
}