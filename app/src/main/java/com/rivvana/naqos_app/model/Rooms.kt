package com.rivvana.naqos_app.model

data class Rooms (
    var createdAt: String? = null,
    var updatedAt: Any? = null,
    var id: String? = null,
    var roomType: String? = null,
    var rules: String? = null,
    var pricePerDaily: Double? = null,
    var pricePerWeekly: Double? = null,
    var pricePerMonthly: Double? = null,
    var isAvailable: Boolean? = null,
    var facilities: List<Any>? = null
)