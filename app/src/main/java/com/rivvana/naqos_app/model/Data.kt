package com.rivvana.naqos_app.model

data class Data(
    var address: String? = null,
    var city: City? = null,
    var createdAt: String? = null,
    var description: String? = null,
    var district: String? = null,
    var id: String? = null,
    var imageKosts: List<ImageKost> = arrayListOf<ImageKost>(),
    var isAvailable: Boolean? = null,
    var kostType: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var name: String? = null,
    var ownerId: OwnerId? = null,
    var postalCode: String? = null,
    var rooms: List<Any?>? = null,
    var subdistrict: String? = null,
    var updatedAt: Any? = null
)
