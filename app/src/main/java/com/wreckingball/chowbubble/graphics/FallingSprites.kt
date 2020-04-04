package com.wreckingball.chowbubble.graphics

import android.graphics.Canvas
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.random.Random

private const val SPEED_UP_INTERVAL = 5000
private const val SPEED_UP_INCREMENT = 10
private const val DROP_MULTIPLIER = 0.005f

class FallingSprites : KoinComponent {
    val fallingSprites: MutableList<Sprite> = arrayListOf()
    private val screenUtils: ScreenUtils by inject()
    private val fallingSpriteFactory: FallingSpriteFactory by inject()
    private var newSpriteTime = 1000
    private var dropSpeed: Int
    private var spriteTime: Long
    private var timeToSpeedUp: Long
    private var numSlots: Int = 0

    init {
        spriteTime = System.currentTimeMillis() + newSpriteTime
        timeToSpeedUp = System.currentTimeMillis() + SPEED_UP_INTERVAL
        dropSpeed = (screenUtils.screenDims.y.toFloat() * DROP_MULTIPLIER).toInt()
    }

    fun reset() {
        fallingSprites.clear()
    }

    fun onUpdate() {
        val currentTime = System.currentTimeMillis()
        if (currentTime > timeToSpeedUp) {
            //decrease drop interval
            newSpriteTime -= SPEED_UP_INCREMENT
            if (newSpriteTime < 0) {
                newSpriteTime = 0
            }
            timeToSpeedUp = System.currentTimeMillis() + SPEED_UP_INTERVAL
        }

        if (currentTime > spriteTime) {
            //drop a new sprite
            val newSprite = getSprite()
            if (newSprite != null) {
                fallingSprites.add(newSprite)
                spriteTime = System.currentTimeMillis() + newSpriteTime
            }
        }

        val iterator = fallingSprites.iterator()
        while (iterator.hasNext()) {
            //update falling sprites
            val sprite = iterator.next()
            if (sprite.y < screenUtils.screenDims.y) {
                sprite.moveY(dropSpeed.toFloat())
                sprite.onUpdate()
            } else {
                iterator.remove()
            }
        }
    }

    fun onDraw(canvas: Canvas) {
        for (sprite in fallingSprites) {
            sprite.onDraw(canvas)
        }
    }

    private fun getSprite() : Sprite? {
        var sprite: Sprite? = null
        var num = Random.nextInt(100)
        if (num > 95) {
            num = Random.nextInt(10)
            sprite = fallingSpriteFactory.getSprite(num, Random.nextInt(2) + 1)
        }

        if (sprite != null) {
            if (numSlots == 0) {
                numSlots = screenUtils.screenDims.x / sprite.getWidth() - 1
            }

            num = Random.nextInt(numSlots)
            val xPos = (screenUtils.screenDims.x / numSlots * num).toFloat()
            sprite.setXY(xPos, 0f)
        }
        return sprite
    }
}