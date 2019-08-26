package com.wreckingball.chowbubble.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.wreckingball.chowbubble.controllers.ChowController
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import com.wreckingball.chowbubble.views.ChowView
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module(override = true) {
    single { PreferencesWrapper(get()) }
    single { getSharedPrefs(androidContext()) }
    single { ChowSongs() }
    single { ChowView(androidContext(), get()) }
    single { ChowController(androidContext()) }
}

fun getSharedPrefs(context: Context) : SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}