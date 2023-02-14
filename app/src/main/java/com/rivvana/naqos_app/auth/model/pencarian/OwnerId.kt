package com.rivvana.naqos_app.auth.model.pencarian

data class OwnerId(
    val bankAccount: Any,
    val fullname: String,
    val id: Int,
    val imgUrl: String,
    val phoneNumber: String,
    val providers: String,
    val username: String
)