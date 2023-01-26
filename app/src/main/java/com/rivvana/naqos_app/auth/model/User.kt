package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("fullname")
    val fullname: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String,
    //email = username
    @SerializedName("username")
    val username: String,
        )