package com.wreckingball.chowbubble.activities

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.animations.PulseAnimation
import com.wreckingball.chowbubble.controllers.CHOW_SONGS_KEY
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import kotlinx.android.synthetic.main.activity_gameover.*
import org.koin.android.ext.android.inject

const val NEW_SCORE_KEY = "new_score"

class ActivityGameOver : AppCompatActivity() {
    private val HIGH_SCORE_KEY = "high_score"
    private val preferences: PreferencesWrapper by inject()
    private val chowSongs: ChowSongs by inject()
    private var soundOn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameover)

        displayScore()

        button_over_sound.setOnClickListener {
            soundOn = !soundOn
            handleSoundButton(soundOn)
        }

        button_over_instructions.setOnClickListener {
            val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)
            pulse.setAnimationListener(PulseAnimation(this, ActivityInstructions::class.java))
            button_over_instructions.startAnimation(pulse)
        }

        button_playagain.setOnClickListener {
            val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)
            pulse.setAnimationListener(PulseAnimation(this, ActivityGame::class.java))
            button_playagain.startAnimation(pulse)
        }
    }

    override fun onResume() {
        super.onResume()
        soundOn = preferences.getBoolean(CHOW_SONGS_KEY, true)
        handleSoundButton(soundOn)
    }

    private fun handleSoundButton(isOn: Boolean) {
        if (isOn) {
            button_over_sound.setImageResource(R.drawable.sound_on)
            preferences.putBoolean(CHOW_SONGS_KEY, true)
            chowSongs.play(this, R.raw.main_theme)
        } else {
            button_over_sound.setImageResource(R.drawable.sound_off)
            preferences.putBoolean(CHOW_SONGS_KEY, false)
            chowSongs.pause()
        }
    }

    private fun displayScore() {
        val newScore = intent.getIntExtra(NEW_SCORE_KEY, 0)
        new_score.text = getString(R.string.new_score, newScore)

        val bestScore = preferences.getInt(HIGH_SCORE_KEY, 0)
        if (newScore > bestScore) {
            preferences.putInt(HIGH_SCORE_KEY, newScore)
            best_score.text = getString(R.string.best_score, newScore)
        } else {
            best_score.text = getString(R.string.best_score, bestScore)
        }

    }
}