package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName


data class RegisterResponse (
    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: String,

    @SerializedName("status")
    var message: String,
        )