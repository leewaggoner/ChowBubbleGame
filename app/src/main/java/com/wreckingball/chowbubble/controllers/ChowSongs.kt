package com.wreckingball.chowbubble.controllers

import android.content.Context
import android.media.MediaPlayer

const val CHOW_SONGS_KEY = "ChowSongsKey"

class ChowSongs {
    private var mediaPlayer : MediaPlayer? = null

    fun play(context: Context, theme: Int) {
        mediaPlayer = MediaPlayer.create(context, theme)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.stop()
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}