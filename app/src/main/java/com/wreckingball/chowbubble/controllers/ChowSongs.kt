package com.wreckingball.chowbubble.controllers

import android.content.Context
import android.media.MediaPlayer

const val CHOW_SONGS_KEY = "ChowSongsKey"

class ChowSongs {
    private var mediaPlayer : MediaPlayer? = null
    private var currentTheme = -1

    fun play(context: Context, theme: Int) {
        val isPlaying = mediaPlayer?.isPlaying ?: false
        if (mediaPlayer == null) {
            //create new media player and play the theme
            mediaPlayer = MediaPlayer.create(context, theme)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
            currentTheme = theme
        } else if(theme != currentTheme) {
            //stop and switch themes
            stop()
            mediaPlayer = MediaPlayer.create(context, theme)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
            currentTheme = theme
        } else if (!isPlaying) {
            //resume playing
            mediaPlayer?.start()
        }
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        currentTheme = -1
    }
}