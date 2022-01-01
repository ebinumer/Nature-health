package com.example.naturehealth.session_manager

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SessionManager @SuppressLint("CommitPrefEdits") constructor(private val context: Context)  {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val TOKEN = "api_token"

    /*App open status*/
    var appOpenStatus: Boolean
        get() = pref.getBoolean(APP_OPEN_STATUS, false)
        set(value) {
            editor.putBoolean(APP_OPEN_STATUS, value).apply()
        }

    companion object {
        private const val PREF_NAME = "Nature_Session"
        private const val APP_OPEN_STATUS = "appOpenStatus"
    }
    init {
        // Shared pref mode
        val PRIVATE_MODE = 0
        pref = context.getSharedPreferences(
            PREF_NAME,
            PRIVATE_MODE
        )
        editor = pref.edit()
    }
    fun logout(){
        editor.clear()
        editor.commit()
    }
}