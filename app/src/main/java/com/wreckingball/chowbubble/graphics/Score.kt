package com.wreckingball.chowbubble.graphics

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.controllers.MAX_LIVES
import com.wreckingball.chowbubble.strategies.SpriteDrawStatic
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.KoinComponent
import org.koin.core.inject

class Score : KoinComponent {
    private val screenUtils: ScreenUtils by inject()
    private val lifeSprites: MutableList<Sprite> = arrayListOf()
    private val starMeter: Sprite
    private val starMeterIndicator: Sprite
    private val paint = Paint()
    private var textOffsetX: Int = 0
    private var textOffsetY: Int = 0
    private val dstRect: Rect

    init {
        val screenWidth = screenUtils.screenDims.x
        paint.color = Color.WHITE
        paint.textSize = screenWidth.toFloat() * 0.125f
        val space = screenUtils.pxToDp(80)
        for (i in 0 until MAX_LIVES) {
            val sprite = Sprite(R.drawable.life_full, TYPE_STATIC, null, SpriteDrawStatic())
            sprite.addSprite(R.drawable.life_empty)
            val initialX = space + (i * space) + (i * sprite.getWidth())
            textOffsetX += initialX
            sprite.setXY(initialX.toFloat(), space.toFloat())
            lifeSprites.add(sprite)
        }
        textOffsetX -= lifeSprites[0].getWidth()
        textOffsetY = space + lifeSprites[0].getHeight()

        starMeter = Sprite(R.drawable.starmeter_container, TYPE_STATIC, null, SpriteDrawStatic())
        starMeter.setXY(space.toFloat(), (textOffsetY + space).toFloat())
        starMeterIndicator = Sprite(R.drawable.starmeter_indicator, TYPE_STATIC, null, SpriteDrawStatic())
        val left = starMeter.x.toInt() + 88
        val top = starMeter.y.toInt() + 26
        val right = starMeter.y.toInt() + 220
        val bottom = top + starMeterIndicator.getHeight()
        dstRect = Rect(left, top, right, bottom)
    }

    fun reset() {
        lifeSprites.forEach {
            it.currentSprite = 0
        }
    }

    fun decrementLife(lives: Int) {
        lifeSprites[lives].incrementCurrentSprite()
    }

    fun drawLives(canvas: Canvas, life: Int) {
        for (i in 0 until life) {
            lifeSprites[i].onDraw(canvas)
        }
    }

    fun drawScore(canvas: Canvas, score: Int) {
        canvas.drawText(
            score.toString(),
            textOffsetX.toFloat(),
            textOffsetY.toFloat(),
            paint
        )
    }

    fun onDraw(canvas: Canvas, score: Int, bonusTime: Double) {
        drawLives(canvas, MAX_LIVES)
        drawScore(canvas, score)
        if (bonusTime > 0) {
            drawMeter(canvas, bonusTime)
        }
    }

    private fun drawMeter(canvas: Canvas, bonusTime: Double) {
        starMeter.onDraw(canvas)
        val length = (bonusTime * (dstRect.right - dstRect.left)).toInt()
        val indicatorRect = Rect(dstRect)
        indicatorRect.right = indicatorRect.left + length
        canvas.drawBitmap(starMeterIndicator.getBitmap(), null, indicatorRect, null)
    }
}