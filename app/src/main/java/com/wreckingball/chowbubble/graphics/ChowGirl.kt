package com.wreckingball.chowbubble.graphics

import android.graphics.Canvas
import android.graphics.RectF
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.strategies.SpriteDrawStatic
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.KoinComponent
import org.koin.core.inject

const val GIRL_STANDING = 0
const val GIRL_CATCHING = 1

class ChowGirl : KoinComponent{
    private val GIRL_START_Y_MODIFIER = 0.65f

    var halfWidth: Float
    val quarterWidth: Float
    private val screenUtils: ScreenUtils by inject()
    private val sprite = Sprite(R.drawable.chow_nocatch, TYPE_STATIC, null, SpriteDrawStatic())

    init {
        sprite.addSprite(R.drawable.chow_catch)

        //make hit box smaller
        val width = sprite.getWidth().toFloat()
        halfWidth = width / 2
        quarterWidth = halfWidth / 2
        sprite.box.left += quarterWidth
        sprite.box.right -= quarterWidth
        sprite.box.bottom -= (sprite.getHeight() / 2).toFloat()
        setXY(screenUtils.screenDims.x / 2 - halfWidth, screenUtils.screenDims.y * GIRL_START_Y_MODIFIER)
    }

    fun reset() {
        setXY(screenUtils.screenDims.x / 2 - halfWidth, screenUtils.screenDims.y * GIRL_START_Y_MODIFIER)
    }

    fun setGirlCatching() {
        sprite.currentSprite = GIRL_CATCHING
    }

    fun setGirlStanding() {
        sprite.currentSprite = GIRL_STANDING
    }

    fun getX() : Float {
        return sprite.x
    }

    private fun setXY(x: Float, y: Float) {
        sprite.setGirlXY(x, y, quarterWidth)
    }

    fun moveX(x: Float) {
        sprite.moveX(x)
    }

    fun intersects(rect: RectF): Boolean {
        return RectF.intersects(sprite.box, rect)
    }

    fun onDraw(canvas: Canvas) {
        sprite.onDraw(canvas)
    }
}