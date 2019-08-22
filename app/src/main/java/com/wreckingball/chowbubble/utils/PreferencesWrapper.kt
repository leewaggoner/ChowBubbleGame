package com.wreckingball.chowbubble.utils

import android.content.SharedPreferences

class PreferencesWrapper(sharedPreferences: SharedPreferences) {
    private var preferences = sharedPreferences

    fun getBoolean(key: String, default: Boolean) : Boolean {
        return preferences.getBoolean(key, default);
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().also {
            it.putBoolean(key, value)
            it.apply()
        }
    }
}