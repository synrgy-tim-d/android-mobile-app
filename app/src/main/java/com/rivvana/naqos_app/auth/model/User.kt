package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("id")
    val id: Int,
    //email = username
    @SerializedName("username")
    val username: String,

    @SerializedName("fullname")
    val fullname: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String

        )