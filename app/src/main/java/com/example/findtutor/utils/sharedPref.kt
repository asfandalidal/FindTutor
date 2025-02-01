package com.example.findtutor.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {

    private const val PREFS_NAME = "find_tutor_prefs"
    private const val KEY_FIRST_TIME_LAUNCH = "first_time_launch"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isFirstTimeLaunch(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_FIRST_TIME_LAUNCH, true)
    }

    fun setFirstTimeLaunch(context: Context, isFirstTime: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_FIRST_TIME_LAUNCH, isFirstTime).apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }
}
