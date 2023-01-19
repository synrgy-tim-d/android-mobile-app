package com.rivvana.naqos_app.auth.model

data class DataX(
    var authorities: List<Authority?>?,
    var fullname: String?,
    var id: Int?,
    var otp: String?,
    var otpExpiredDate: String?,
    var phoneNumber: String?,
    var roles: List<Role?>?,
    var username: String?
)