package com.rivvana.naqos_app.auth.model

import com.google.gson.annotations.SerializedName

data class UpdateDataRequest (
    @SerializedName("fullname")
    val fullname: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String,
        )