package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

class LoginResponse {
    lateinit var message: String
    var data = Data()
}

class Data(){
    var user_id: Int = 0
    var code: Int = 0
    lateinit var access_token: String
    lateinit var status: String
    lateinit var refresh_token: String
}