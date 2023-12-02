package com.wreckingball.chowbubble.controllers

import android.graphics.Canvas
import android.graphics.RectF
import com.wreckingball.chowbubble.graphics.ChowGirl
import com.wreckingball.chowbubble.states.ChowGirlCatchingState
import com.wreckingball.chowbubble.states.ChowGirlStandingState
import com.wreckingball.chowbubble.states.ChowGirlState
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChowGirlController : KoinComponent {
    private val screenUtils: ScreenUtils by inject()
    private val chowGirl: ChowGirl by inject()
    private val chowGirlStandingState: ChowGirlStandingState = ChowGirlStandingState(chowGirl)
    private val chowGirlCatchingState: ChowGirlCatchingState = ChowGirlCatchingState(chowGirl, this)
    private var currentState: ChowGirlState = chowGirlStandingState
    private val moveIncrement: Float

    init {
        moveIncrement = screenUtils.screenDims.x.toFloat() * 0.0125f
        currentState.init()
    }

    fun reset() {
        chowGirl.reset()
    }

    fun stand() {
        currentState = chowGirlStandingState
        currentState.init()
    }

    fun intersects(box: RectF) : Boolean {
        return chowGirl.intersects(box)
    }

    fun catchItem() {
        currentState = chowGirlCatchingState
        currentState.init()
    }

    fun onUpdate(touchDown: Boolean, touchX: Float) {
        currentState.doAction()
        if (touchDown) {
            val x = chowGirl.getX()
            if (touchX < x + chowGirl.quarterWidth) {
                chowGirl.moveX(-moveIncrement)
            } else if (touchX > x + chowGirl.halfWidth + chowGirl.quarterWidth) {
                chowGirl.moveX(moveIncrement)
            }
        }
    }

    fun onDraw(canvas: Canvas) {
        chowGirl.onDraw(canvas)
    }
}