package com.rivvana.naqos_app.auth.model

data class UserRegister (
    val username: String,
    val phoneNumber: String,
    val fullname: String,
    val password: String
)