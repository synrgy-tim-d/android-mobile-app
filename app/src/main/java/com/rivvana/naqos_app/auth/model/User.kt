package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

class User {
    var  id: Int = 0
    lateinit var username: String
    lateinit var fullname: String
    lateinit var phoneNumber: String
}