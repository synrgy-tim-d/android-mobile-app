package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("fullname")
    val fullname: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("role")
    val role: String

)