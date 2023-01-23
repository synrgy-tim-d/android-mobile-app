package com.rivvana.naqos_app.auth.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPref(activity: Activity) {

    val login = "login"
    val mypref = "MAIN_PRF"
    val sharedPref: SharedPreferences

    init {
        sharedPref = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean){
        sharedPref.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin(): Boolean{
        return sharedPref.getBoolean(login, false)
    }
}