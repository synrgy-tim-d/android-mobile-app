package com.rivvana.naqos_app.auth.viewmodel

import android.content.Context
import android.content.SharedPreferences
import com.rivvana.naqos_app.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences
    = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    companion object {
        const val USER_TOKEN = "user_token"
    }
    //Function to save auth token
    fun saveAuthToken(token: String?) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }
    //Function to fetch auth token
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
    //remove token
    fun removeToken() {
       val editor = prefs.edit()
        editor.putString(USER_TOKEN, null)
        editor.apply()
    }

    fun clearSession() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}