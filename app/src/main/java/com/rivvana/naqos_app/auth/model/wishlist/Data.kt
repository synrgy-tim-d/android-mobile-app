package com.rivvana.naqos_app.auth.model.wishlist

data class Data(
    val address: String,
    val city: City,
    val description: String,
    val district: String,
    val id: String,
    val imgUrl: List<String>,
    val isAvailable: Boolean,
    val kostType: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val postalCode: String,
    val subdistrict: String,
    val user: User
)