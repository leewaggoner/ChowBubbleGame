package com.wreckingball.chowbubble.activities

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.animations.PulseAnimation
import com.wreckingball.chowbubble.controllers.CHOW_SONGS_KEY
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.controllers.ChowSounds
import com.wreckingball.chowbubble.databinding.ActivityMainMenuBinding
import com.wreckingball.chowbubble.di.setActivity
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import org.koin.android.ext.android.inject

class ActivityMainMenu : AppCompatActivity() {
    private val preferences: PreferencesWrapper by inject()
    private val chowSongs: ChowSongs by inject()
    private val chowSounds: ChowSounds by inject()
    private var soundOn: Boolean = true
    private var binding: ActivityMainMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setActivity(this)

        binding?.buttonSound?.setOnClickListener {
            soundOn = !soundOn
            handleSoundButton(soundOn)
        }

        binding?.buttonInstructions?.setOnClickListener {
            val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)
            pulse.setAnimationListener(PulseAnimation(this, ActivityInstructions::class.java))
            binding?.buttonInstructions?.startAnimation(pulse)
        }

        binding?.buttonPlay?.setOnClickListener {
            val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)
            pulse.setAnimationListener(PulseAnimation(this, ActivityGame::class.java))
            binding?.buttonPlay?.startAnimation(pulse)
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
            binding?.buttonSound?.setImageResource(R.drawable.sound_on)
            preferences.putBoolean(CHOW_SONGS_KEY, true)
            chowSongs.play(this, R.raw.main_theme)
        } else {
            binding?.buttonSound?.setImageResource(R.drawable.sound_off)
            preferences.putBoolean(CHOW_SONGS_KEY, false)
            chowSongs.pause()
        }
    }
}