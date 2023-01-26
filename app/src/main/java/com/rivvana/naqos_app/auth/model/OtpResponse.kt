package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

data class OtpResponse (

    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("status")
    var status: String,

        )