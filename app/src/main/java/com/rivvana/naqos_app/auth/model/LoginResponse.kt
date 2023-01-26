package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status")
    var status: String,

    @SerializedName("access_token")
    var access_token: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("user_id")
    var user_id: Int
        )