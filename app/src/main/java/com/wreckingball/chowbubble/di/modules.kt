package com.wreckingball.chowbubble.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.chowbubble.controllers.ChowController
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.graphics.Background
import com.wreckingball.chowbubble.graphics.Clouds
import com.wreckingball.chowbubble.graphics.Moon
import com.wreckingball.chowbubble.graphics.SpriteBitmapFactory
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import com.wreckingball.chowbubble.utils.ScreenUtils
import com.wreckingball.chowbubble.views.ChowView
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private lateinit var appActivity: AppCompatActivity

val appModule = module(override = true) {
    single { PreferencesWrapper(get()) }
    single { getSharedPrefs(androidContext()) }
    single { ChowSongs() }
    single { ChowView(androidContext(), get()) }
    single { ChowController(androidContext()) }
    single { ScreenUtils(androidContext(), appActivity) }
    single { SpriteBitmapFactory(androidContext()) }
    single { Background() }
    single { Moon() }
    single { Clouds() }
}

private fun getSharedPrefs(context: Context) : SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

fun setActivity(activity: AppCompatActivity) {
    appActivity = activity
}