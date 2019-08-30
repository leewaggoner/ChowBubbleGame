package com.wreckingball.chowbubble.states

import com.wreckingball.chowbubble.graphics.ChowGirl

class ChowGirlCatchingState(private val chowGirl: ChowGirl) : ChowGirlState {
    private val CHOW_CATCH_ANIMATE_LENGTH = 500
    private var timeToAnimate: Long = 0

    override fun init() {
        chowGirl.setGirlCatching()
        timeToAnimate = System.currentTimeMillis() + CHOW_CATCH_ANIMATE_LENGTH
    }

    override fun doAction() {
        if (System.currentTimeMillis() > timeToAnimate) {
            chowGirl.setGirlStanding()
        }
    }
}