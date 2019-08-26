package com.wreckingball.chowbubble.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import kotlinx.android.synthetic.main.activity_game.*

class ActivityGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onResume() {
        super.onResume()
        chow_view.resume()
    }

    override fun onPause() {
        super.onPause()
        chow_view.pause()
    }
}