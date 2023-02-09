package com.rivvana.naqos_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "datakos")
data class Data(
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
    var rooms: Rooms? = null,
    var subdistrict: String? = null,
    var updatedAt: Any? = null
)
