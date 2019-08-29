package com.wreckingball.chowbubble.controllers

import android.graphics.Canvas
import com.wreckingball.chowbubble.graphics.Score
import org.koin.core.KoinComponent
import org.koin.core.inject

const val MAX_LIVES = 3
const val BONUS_TIME = 10000

class ScoreController : KoinComponent {
    private val BOMB_PENALTY = 3
    private val VEGGIE_PENALTY = 1
    private val BUBBLE_POINTS = 10
    private val STAR_BONUS = 3

    var gameOver = false
    var curScore = 0

    private var lives = MAX_LIVES
    private var bonusTime: Long = 0

    private val score: Score by inject()

    private fun decrementLives(penalty: Int) {
        var i = penalty
        while (i > 0 && lives > 0) {
            lives--
            score.decrementLife(lives)
            i--
        }

        if (lives == 0) {
            gameOver = true
        }
    }

    private fun incrementScore() {
        var points = BUBBLE_POINTS
        if (bonusTime > 0) {
            points *= STAR_BONUS
        }
        curScore += points
    }

    private fun setBonusTime() {
        bonusTime = System.currentTimeMillis() + BONUS_TIME
    }

    fun scoreBomb() {
        decrementLives(BOMB_PENALTY)
    }

    fun scoreVeggie() {
        decrementLives(VEGGIE_PENALTY)
    }

    fun scoreStar() {
        setBonusTime()
    }

    fun scoreBubbleTea() {
        incrementScore()
    }

    private fun handleBonusTime() : Double {
        val time = bonusTime - System.currentTimeMillis()
        var value: Double = 0.toDouble()
        if (time > 0) {
            value = time.toDouble() / BONUS_TIME.toDouble()
        } else {
            bonusTime = 0
        }

        return value
    }

    fun onDraw(canvas: Canvas) {
        score.onDraw(canvas, curScore, handleBonusTime())
    }
}