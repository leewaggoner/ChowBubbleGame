package com.wreckingball.chowbubble.states

import android.graphics.Canvas
import com.wreckingball.chowbubble.controllers.*
import com.wreckingball.chowbubble.graphics.*
import com.wreckingball.chowbubble.strategies.SpriteDrawAlphaFade
import com.wreckingball.chowbubble.strategies.SpriteUpdateFade

class GamePlayingState(private val chowController: ChowController,
                       private val moon: Moon,
                       private val clouds: Clouds,
                       private val chowGirlController: ChowGirlController,
                       private val fallingSprites: FallingSprites,
                       private val scoreController: ScoreController,
                       private val chowSounds: ChowSounds) : GameState {
    override fun init() {
        fallingSprites.reset()
    }

    override fun onUpdate(touchDown: Boolean, touchX: Float) {
        clouds.onUpdate()
        fallingSprites.onUpdate()
        chowGirlController.onUpdate(touchDown, touchX)
        val sprites = fallingSprites.fallingSprites
        val iterator = sprites.iterator()
        while (iterator.hasNext()) {
            val sprite = iterator.next()
            if (chowGirlController.intersects(sprite.box)) {
                when (sprite.spriteType) {
                    TYPE_BOMB -> handleBomb(sprite)
                    TYPE_VEGGIE -> {
                        handleVeggie()
                        iterator.remove()
                    }
                    TYPE_STAR -> {
                        handleStar()
                        iterator.remove()
                    }
                    TYPE_BUBBLE_TEA -> {
                        handleBubbleTea()
                        iterator.remove()
                    }
                }
                chowGirlController.catchItem()
                if (scoreController.gameOver) {
                    chowController.endGame()
                }
            }
        }
    }

    override fun onDraw(frameBufferCanvas: Canvas) {
        moon.onDraw(frameBufferCanvas)
        clouds.onDraw(frameBufferCanvas)
        scoreController.onDraw(frameBufferCanvas)
        fallingSprites.onDraw(frameBufferCanvas)
        chowGirlController.onDraw(frameBufferCanvas)
    }

    private fun handleBomb(sprite: Sprite) {
        //instant loss
        moon.setCurrentSprite(MOON_FACE_SAD)
        scoreController.scoreBomb()
        sprite.incrementCurrentSprite()
        sprite.drawStrategy = SpriteDrawAlphaFade()
        sprite.updateStrategy = SpriteUpdateFade()
        chowSounds.play(BOMB_EXPLODE)
    }

    private fun handleVeggie() {
        moon.setCurrentSprite(MOON_FACE_SAD)
        scoreController.scoreVeggie()
        chowSounds.play(VEGGIE_CATCH)
    }

    private fun handleStar() {
        if (moon.getCurrentSprite() == MOON_FACE_HAPPY_1) {
            moon.setCurrentSprite(MOON_FACE_HAPPY_2)
        } else {
            moon.setCurrentSprite(MOON_FACE_HAPPY_1)
        }
        scoreController.scoreStar()
        chowSounds.play(STAR_CATCH)
    }

    private fun handleBubbleTea() {
        if (moon.getCurrentSprite() == MOON_FACE_HAPPY_1) {
            moon.setCurrentSprite(MOON_FACE_HAPPY_2)
        } else {
            moon.setCurrentSprite(MOON_FACE_HAPPY_1)
        }
        scoreController.scoreBubbleTea()
        chowSounds.play(BUBBLE_CATCH)
    }
}