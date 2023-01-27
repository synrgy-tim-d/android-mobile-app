package com.rivvana.naqos_app.auth.viewmodel

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.model.Data
import com.rivvana.naqos_app.auth.model.User

class SessionManager (context: Context) {

    val user = "User"

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
    //get user
    fun setUser(value: User) {
        val data: String = Gson().toJson(value, User::class.java)
        prefs.edit().putString(user, data).apply()
    }
    //Get data user
    fun saveString(key: String, value: String){
        val editor = prefs.edit()
        editor.putString(key, value)
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