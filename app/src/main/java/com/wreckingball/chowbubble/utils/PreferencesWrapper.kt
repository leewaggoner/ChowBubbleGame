package com.wreckingball.chowbubble.utils

import android.content.SharedPreferences

class PreferencesWrapper(sharedPreferences: SharedPreferences) {
    private val preferences = sharedPreferences

    fun getBoolean(key: String, default: Boolean) : Boolean {
        return preferences.getBoolean(key, default)
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().also {
            it.putBoolean(key, value)
            it.apply()
        }
    }

    fun getInt(key: String, default: Int) : Int {
        return preferences.getInt(key, default)
    }

    fun putInt(key: String, value: Int) {
        preferences.edit().also {
            it.putInt(key, value)
            it.apply()
        }
    }
}