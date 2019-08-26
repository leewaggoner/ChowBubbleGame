package com.wreckingball.chowbubble.animations

import android.content.Intent
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity

class PulseAnimation(private val activity: AppCompatActivity, private val clazz: Class<*>) : Animation.AnimationListener {
    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        val intent = Intent(activity, clazz)
        activity.startActivity(intent)
    }
}