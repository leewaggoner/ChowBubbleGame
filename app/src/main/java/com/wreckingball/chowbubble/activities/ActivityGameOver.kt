package com.wreckingball.chowbubble.activities

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.animations.PulseAnimation
import com.wreckingball.chowbubble.controllers.CHOW_SONGS_KEY
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.controllers.ChowSounds
import com.wreckingball.chowbubble.databinding.ActivityGameoverBinding
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import org.koin.android.ext.android.inject

const val NEW_SCORE_KEY = "new_score"

private const val HIGH_SCORE_KEY = "high_score"

class ActivityGameOver : AppCompatActivity() {
    private val preferences: PreferencesWrapper by inject()
    private val chowSongs: ChowSongs by inject()
    private val chowSounds: ChowSounds by inject()
    private var soundOn: Boolean = true
    private var binding: ActivityGameoverBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameoverBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        displayScore()

        binding?.buttonOverSound?.setOnClickListener {
            soundOn = !soundOn
            handleSoundButton(soundOn)
        }

        binding?.buttonOverInstructions?.setOnClickListener {
            val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)
            pulse.setAnimationListener(PulseAnimation(this, ActivityInstructions::class.java))
            binding?.buttonOverInstructions?.startAnimation(pulse)
        }

        binding?.buttonPlayagain?.setOnClickListener {
            val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)
            pulse.setAnimationListener(PulseAnimation(this, ActivityGame::class.java))
            binding?.buttonPlayagain?.startAnimation(pulse)
        }
    }

    override fun onResume() {
        super.onResume()
        soundOn = preferences.getBoolean(CHOW_SONGS_KEY, true)
        handleSoundButton(soundOn)
    }

    private fun handleSoundButton(isOn: Boolean) {
        chowSounds.isOn = isOn
        if (isOn) {
            binding?.buttonOverSound?.setImageResource(R.drawable.sound_on)
            preferences.putBoolean(CHOW_SONGS_KEY, true)
            chowSongs.play(this, R.raw.main_theme)
        } else {
            binding?.buttonOverSound?.setImageResource(R.drawable.sound_off)
            preferences.putBoolean(CHOW_SONGS_KEY, false)
            chowSongs.pause()
        }
    }

    private fun displayScore() {
        val newScore = intent.getIntExtra(NEW_SCORE_KEY, 0)
        binding?.newScore?.text = getString(R.string.new_score, newScore)

        val bestScore = preferences.getInt(HIGH_SCORE_KEY, 0)
        if (newScore > bestScore) {
            preferences.putInt(HIGH_SCORE_KEY, newScore)
            binding?.bestScore?.text = getString(R.string.best_score, newScore)
        } else {
            binding?.bestScore?.text = getString(R.string.best_score, bestScore)
        }

    }
}