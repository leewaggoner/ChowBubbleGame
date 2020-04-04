package com.wreckingball.chowbubble.controllers

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import com.wreckingball.chowbubble.R

const val BOMB_EXPLODE = "bomb"
const val BUBBLE_CATCH = "bubble_catch"
const val STAR_CATCH = "star_catch"
const val VEGGIE_CATCH = "veggie_catch"

private const val MAX_STREAMS = 4

class ChowSounds {
    private var soundPool: SoundPool? = null
    private val sound: MutableMap<String, Int?> = hashMapOf()
    var isOn = true

    fun init(context: Context) {
        soundPool ?:  run {
            soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()

                SoundPool.Builder()
                    .setMaxStreams(25)
                    .setAudioAttributes(audioAttributes)
                    .build()
            } else {
                SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0)
            }
            sound[BOMB_EXPLODE] = soundPool?.load(context, R.raw.bomb, 1)
            sound[BUBBLE_CATCH] = soundPool?.load(context, R.raw.bubble_catch, 1)
            sound[STAR_CATCH] = soundPool?.load(context, R.raw.star_catch, 1)
            sound[VEGGIE_CATCH] = soundPool?.load(context, R.raw.veggie_catch, 1)
        }
    }

    fun play(id: String) {
        if (isOn) {
            sound[id]?.let {
                soundPool?.play(it, 0.5f, 0.5f, 0, 0, 1.0f)
            }
        }
    }

    fun release() {
        soundPool?.release()
        soundPool = null
    }
}