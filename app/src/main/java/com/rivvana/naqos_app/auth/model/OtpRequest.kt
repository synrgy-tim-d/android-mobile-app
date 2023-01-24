package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

data class OtpRequest (
    @SerializedName("username")
    val username: String
        )