package com.wreckingball.chowbubble.states

import android.graphics.Canvas
import com.wreckingball.chowbubble.controllers.*
import com.wreckingball.chowbubble.graphics.Clouds
import com.wreckingball.chowbubble.graphics.Moon

private const val POPIN_INTERVAL = 500

class GameStartState(private val chowController: ChowController,
                     private val moon: Moon,
                     private val clouds: Clouds,
                     private val chowGirlController: ChowGirlController,
                     private val scoreController: ScoreController,
                     private val chowSounds: ChowSounds) : GameState {
    private var lifePopin: Long = 0
    private var lives = 0

    override fun init() {
        lives = 0
        lifePopin = System.currentTimeMillis() + POPIN_INTERVAL
        scoreController.reset()
        moon.reset()
        chowGirlController.reset()
    }

    override fun onUpdate(touchDown: Boolean, touchX: Float) {
        clouds.onUpdate()
        chowGirlController.onUpdate(touchDown, touchX)
        if (System.currentTimeMillis() > lifePopin) {
            lifePopin = System.currentTimeMillis() + POPIN_INTERVAL
            if (lives < MAX_LIVES) {
                lives++
                chowSounds.play(STAR_CATCH)
            } else {
                //start playing
                chowController.playGame()
            }
        }
    }

    override fun onDraw(frameBufferCanvas: Canvas) {
        moon.onDraw(frameBufferCanvas)
        clouds.onDraw(frameBufferCanvas)
        chowGirlController.onDraw(frameBufferCanvas)
        scoreController.drawScore(frameBufferCanvas)
        scoreController.drawLives(frameBufferCanvas, lives)
    }
}