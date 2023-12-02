package com.wreckingball.chowbubble.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.controllers.CHOW_SONGS_KEY
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.databinding.ActivityGameBinding
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import org.koin.android.ext.android.inject

class ActivityGame : AppCompatActivity() {
    private val preferences: PreferencesWrapper by inject()
    private val chowSongs: ChowSongs by inject()
    private var binding: ActivityGameBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onResume() {
        super.onResume()
        if (preferences.getBoolean(CHOW_SONGS_KEY, true)) {
            chowSongs.play(this, R.raw.gameplay_song)
        }
        binding?.chowView?.resume()
    }

    override fun onPause() {
        super.onPause()
        binding?.chowView?.pause()
    }
}