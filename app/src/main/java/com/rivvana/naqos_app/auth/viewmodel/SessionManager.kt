package com.rivvana.naqos_app.auth.viewmodel

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.model.Data
import com.rivvana.naqos_app.auth.model.User

class SessionManager (context: Context) {

    val user = "user"
    val login = "login"

    private var prefs: SharedPreferences
    = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }
    //set user
    fun setUser(value: User) {
        val data: String = Gson().toJson(value, User::class.java)
        prefs.edit().putString(user, data).apply()
    }
    //Get data user
    fun getUser(): User? {
        val data:String = prefs.getString(user, null) ?: return null
        return Gson().fromJson<User>(data, User::class.java)
    }
    //set status login
    fun setStatusLogin(status: Boolean){
        prefs.edit().putBoolean(login, status).apply()
    }
    //get status login
    fun getStatusLogin(): Boolean{
        return prefs.getBoolean(login, false)
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