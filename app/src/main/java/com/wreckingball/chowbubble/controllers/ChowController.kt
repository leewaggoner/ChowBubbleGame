package com.wreckingball.chowbubble.controllers

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.MotionEvent
import com.wreckingball.chowbubble.activities.ActivityGameOver
import com.wreckingball.chowbubble.activities.NEW_SCORE_KEY
import com.wreckingball.chowbubble.graphics.Background
import com.wreckingball.chowbubble.graphics.Clouds
import com.wreckingball.chowbubble.graphics.FallingSprites
import com.wreckingball.chowbubble.graphics.Moon
import com.wreckingball.chowbubble.states.GameOverState
import com.wreckingball.chowbubble.states.GamePlayingState
import com.wreckingball.chowbubble.states.GameStartState
import com.wreckingball.chowbubble.states.GameState
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.KoinComponent
import org.koin.core.inject

class ChowController(private val context: Context) : KoinComponent {
    private val screenUtils: ScreenUtils by inject()
    private val background: Background by inject()
    private val moon: Moon by inject()
    private val clouds: Clouds by inject()
    private val scoreController: ScoreController by inject()
    private val chowGirlController: ChowGirlController by inject()
    private val fallingSprites: FallingSprites by inject()
    private val chowSounds: ChowSounds by inject()
    private var gameState: GameState? = null
    private val gameStartState = GameStartState(this, moon, clouds, chowGirlController, scoreController, chowSounds)
    private val gamePlayingState = GamePlayingState(this, moon, clouds, chowGirlController, fallingSprites, scoreController, chowSounds)
    private val gameOverState = GameOverState(this, moon, clouds, chowGirlController, fallingSprites, scoreController)
    private val frameBufferBitmap = Bitmap.createBitmap(screenUtils.screenDims.x, screenUtils.screenDims.y, Bitmap.Config.ARGB_8888)
    private val frameBufferCanvas = Canvas(frameBufferBitmap)

    private var touchX: Float = 0f
    private var touchDown = false

    fun startGame() {
        gameState = gameStartState
        gameState?.init()
    }

    fun onTouch(event: MotionEvent) {
        touchX = event.x
        if (event.action == MotionEvent.ACTION_DOWN) {
            touchDown = true
        } else if (event.action == MotionEvent.ACTION_UP) {
            touchDown = false
        }
    }

    fun playGame() {
        gameState = gamePlayingState
        gameState?.init()
    }

    fun endGame() {
        gameState = gameOverState
        gameState?.init()
    }

    fun leaveGame(score: Int) {
        val intent = Intent(context, ActivityGameOver::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(NEW_SCORE_KEY, score)
        context.startActivity(intent)
    }

    fun updateChow() {
        gameState?.onUpdate(touchDown, touchX)
    }

    fun drawChow(canvas: Canvas) {
        background.onDraw(frameBufferCanvas)
        gameState?.onDraw(frameBufferCanvas)
        canvas.drawBitmap(frameBufferBitmap, 0f, 0f, null)
    }
}