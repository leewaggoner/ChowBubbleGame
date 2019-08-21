package com.wreckingball.chowbubble.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.controllers.CHOW_SONGS_KEY
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.koin.android.ext.android.inject

class ActivityMainMenu : AppCompatActivity() {
    private val preferences: PreferencesWrapper by inject()
    private var soundOn: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        soundOn = preferences.getBoolean(CHOW_SONGS_KEY, true)
        handleSoundButton(soundOn)
        Log.e("ActivityMainMenu", soundOn.toString())

        button_sound.setOnClickListener {
            soundOn = !soundOn
            handleSoundButton(soundOn)
        }
    }

    private fun handleSoundButton(isOn: Boolean) {
        if (isOn) {
            button_sound.setImageResource(R.drawable.sound_on)
            preferences.putBoolean(CHOW_SONGS_KEY, true)
        } else {
            button_sound.setImageResource(R.drawable.sound_off)
            preferences.putBoolean(CHOW_SONGS_KEY, false)
        }
    }
}