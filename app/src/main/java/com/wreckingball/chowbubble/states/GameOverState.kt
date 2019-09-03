package com.wreckingball.chowbubble.states

import android.graphics.Canvas
import com.wreckingball.chowbubble.controllers.ChowController
import com.wreckingball.chowbubble.controllers.ChowGirlController
import com.wreckingball.chowbubble.controllers.ScoreController
import com.wreckingball.chowbubble.graphics.Clouds
import com.wreckingball.chowbubble.graphics.FallingSprites
import com.wreckingball.chowbubble.graphics.Moon
import com.wreckingball.chowbubble.strategies.SpriteUpdateDescendLeft
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.KoinComponent
import org.koin.core.inject

class GameOverState(private val chowController: ChowController,
                    private val moon: Moon,
                    private val clouds: Clouds,
                    private val chowGirlController: ChowGirlController,
                    private val fallingSprites: FallingSprites,
                    private val scoreController: ScoreController) : GameState, KoinComponent {
    private val CHOW_END_WAIT: Long = 2000
    private val screenUtils: ScreenUtils by inject()
    private var endTime: Long = 0
    var leftGame = false

    override fun init() {
        moon.setUpdateStrategy(SpriteUpdateDescendLeft(screenUtils.screenDims))
        endTime = System.currentTimeMillis() + CHOW_END_WAIT
        leftGame = false
    }

    override fun onUpdate(touchDown: Boolean, touchX: Float) {
        clouds.onUpdate()
        if (System.currentTimeMillis() > endTime && !leftGame) {
            leftGame = true
            chowController.leaveGame(scoreController.curScore)
        }
        moon.onUpdate()
        fallingSprites.onUpdate()
    }

    override fun onDraw(frameBufferCanvas: Canvas) {
        moon.onDraw(frameBufferCanvas)
        clouds.onDraw(frameBufferCanvas)
        scoreController.onDraw(frameBufferCanvas)
        fallingSprites.onDraw(frameBufferCanvas)
        chowGirlController.onDraw(frameBufferCanvas)
    }
}