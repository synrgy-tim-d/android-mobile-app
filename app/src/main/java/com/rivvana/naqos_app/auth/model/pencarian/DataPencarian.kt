package com.rivvana.naqos_app.auth.model.pencarian

data class DataPencarian(
    val currentPage: Int,
    val datapencarian: List<Data> = arrayListOf(),
    val first: Boolean,
    val last: Boolean,
    val size: Int,
    val totalElement: Int,
    val totalPage: Int
)