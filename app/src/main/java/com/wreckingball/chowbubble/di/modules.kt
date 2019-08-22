package com.wreckingball.chowbubble.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module(override = true) {
    single { PreferencesWrapper(get()) }
    single { getSharedPrefs(androidContext()) }
    single { ChowSongs() }
}

fun getSharedPrefs(context: Context) : SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}