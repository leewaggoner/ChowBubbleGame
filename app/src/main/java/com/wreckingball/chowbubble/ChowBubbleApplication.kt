package com.wreckingball.chowbubble

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.wreckingball.chowbubble.controllers.CHOW_SONGS_KEY
import com.wreckingball.chowbubble.controllers.ChowSongs
import com.wreckingball.chowbubble.di.appModule
import com.wreckingball.chowbubble.utils.PreferencesWrapper
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChowBubbleApplication : Application(), LifecycleObserver {
    val chowSongs: ChowSongs by inject()
    val preferences: PreferencesWrapper by inject()
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChowBubbleApplication)
            modules(appModule)
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun OnAppStart() {
        val isOn = preferences.getBoolean(CHOW_SONGS_KEY, false)
        if (isOn) {
            chowSongs.play(this, R.raw.main_theme)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun OnAppResume() {
        chowSongs.play(this, R.raw.main_theme)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun OnAppPause() {
        chowSongs.pause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun OnAppStop() {
        chowSongs.stop()
    }
}