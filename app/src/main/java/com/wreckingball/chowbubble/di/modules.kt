package com.wreckingball.chowbubble.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.controllers.*
import com.wreckingball.chowbubble.graphics.*
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import com.wreckingball.chowbubble.utils.ScreenUtils
import com.wreckingball.chowbubble.views.ChowView
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private lateinit var appActivity: AppCompatActivity

val appModule = module(override = true) {
    single { PreferencesWrapper(getSharedPrefs(androidContext())) }
    single { ChowSongs() }
    single { ChowView(androidContext(), get()) }
    single { ChowController(androidContext()) }
    single { ScreenUtils(androidContext(), appActivity) }
    single { SpriteBitmapFactory(androidContext()) }
    single { Background() }
    single { Moon() }
    single { Clouds() }
    single { Score() }
    single { ScoreController() }
    single { ChowGirl() }
    single { ChowGirlController() }
    single { FallingSpriteFactory() }
    single { FallingSprites() }
    single { ChowSounds() }
}

private fun getSharedPrefs(context: Context) : SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

fun setActivity(activity: AppCompatActivity) {
    appActivity = activity
}