package com.wreckingball.chowbubble.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import kotlinx.android.synthetic.main.activity_game.*

class ActivityGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        game_layout.setOnClickListener {
            val intent = Intent(this, ActivityGameOver::class.java)
            intent.putExtra(NEW_SCORE_KEY, 1000)
            startActivity(intent)
        }
    }
}