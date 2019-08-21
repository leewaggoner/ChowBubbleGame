package com.wreckingball.chowbubble.di

import com.wreckingball.chowbubble.utils.PreferencesWrapper
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module(override = true) {
    single { PreferencesWrapper(androidContext()) }
}