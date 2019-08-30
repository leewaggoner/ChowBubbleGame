package com.wreckingball.chowbubble.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.controllers.CHOW_SONGS_KEY
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.android.ext.android.inject

class ActivityGame : AppCompatActivity() {
    private val preferences: PreferencesWrapper by inject()
    private val chowSongs: ChowSongs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onResume() {
        super.onResume()
        if (preferences.getBoolean(CHOW_SONGS_KEY, true)) {
            chowSongs.play(this, R.raw.gameplay_song)
        }
        chow_view.resume()
    }

    override fun onPause() {
        super.onPause()
        chow_view.pause()
    }
}