package com.wreckingball.chowbubble

import android.app.Application
import com.wreckingball.chowbubble.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChowBubbleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChowBubbleApplication)
            modules(appModule)}
    }
}