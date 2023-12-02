package com.wreckingball.chowbubble.activities

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.animations.PulseAnimation
import com.wreckingball.chowbubble.databinding.ActivityInstructionsBinding

class ActivityInstructions : AppCompatActivity() {
    private var binding: ActivityInstructionsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstructionsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.instButtonPlay?.setOnClickListener {
            val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)
            pulse.setAnimationListener(PulseAnimation(this, ActivityGame::class.java))
            binding?.instButtonPlay?.startAnimation(pulse)
        }
    }
}