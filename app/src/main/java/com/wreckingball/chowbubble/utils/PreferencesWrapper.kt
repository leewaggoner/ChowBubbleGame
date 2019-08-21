package com.wreckingball.chowbubble.utils

import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCES_FILE_KEY = "com.wreckingball.chowbubble"

class PreferencesWrapper(context: Context) {
    private var preferences : SharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

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