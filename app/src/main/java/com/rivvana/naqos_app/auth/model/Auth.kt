package com.rivvana.naqos_app.auth.model

data class Register (
    val fullname: String,
    val phoneNumber: String,
    val username: String,
    val password: String
)

data class Login (
    val username: String,
    val password: String
    )