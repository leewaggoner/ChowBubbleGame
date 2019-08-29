package com.wreckingball.chowbubble.states

import com.wreckingball.chowbubble.graphics.ChowGirl

class ChowGirlStandingState(private val chowGirl: ChowGirl) : ChowGirlState {
    override fun init() {
        chowGirl.setGirlStanding()
    }

    override fun doAction() {
    }
}