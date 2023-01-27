package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("data")
    var data: List<User>
)