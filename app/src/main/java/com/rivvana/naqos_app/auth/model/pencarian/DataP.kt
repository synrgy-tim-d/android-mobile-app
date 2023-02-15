package com.rivvana.naqos_app.auth.model.pencarian

import androidx.room.PrimaryKey
import com.rivvana.naqos_app.model.City
import com.rivvana.naqos_app.model.ImageKost
import com.rivvana.naqos_app.model.OwnerId
import com.rivvana.naqos_app.model.Rooms

data class DataP(
    var kostRating: Double? = null,
    var address: String? = null,
    var city: City? = null,
    var createdAt: String? = null,
    var description: String? = null,
    var district: String? = null,
    @PrimaryKey
    var id: String? = null,
    var imageKosts: List<ImageKost> = arrayListOf(),
    var isAvailable: Boolean? = null,
    var kostType: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var name: String? = null,
    var ownerId: OwnerId? = null,
    var postalCode: String? = null,
    var rooms: List<Rooms> = arrayListOf(),
    var subdistrict: String? = null,
    var updatedAt: Any? = null,
    var question1:String? = null,
    var answer1: String? = null,
    var question2:String? = null,
    var answer2: String? = null,
    var question3:String? = null,
    var answer3: String? = null

//    val address: String,
//    val answer1: String,
//    val answer2: String,
//    val answer3: String,
//    val city: City,
//    val createdAt: Any,
//    val description: String,
//    val district: String,
//    val facilities: List<Facility>,
//    val id: String,
//    val imageKosts: List<ImageKost>,
//    val isAvailable: Boolean,
//    val kostRating: Double,
//    val kostType: String,
//    val latitude: Double,
//    val longitude: Double,
//    val name: String,
//    val ownerId: OwnerId,
//    val postalCode: String,
//    val pricePerDaily: Double,
//    val pricePerMonthly: Double,
//    val pricePerWeekly: Double,
//    val question1: String,
//    val question2: String,
//    val question3: String,
//    val rooms: List<Room>,
//    val rules: String,
//    val subdistrict: String,
//    val updatedAt: Long
)