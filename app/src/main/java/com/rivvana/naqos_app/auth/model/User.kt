package com.rivvana.naqos_app.auth.model

data class User(
    var authorities: List<Authority?>?,
    var fullname: String?,
    var id: Int?,
    var otp: String?,
    var otpExpiredDate: String?,
    var phoneNumber: String?,
    var username: String?
)