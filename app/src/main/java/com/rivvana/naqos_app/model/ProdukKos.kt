package com.rivvana.naqos_app.model

import com.google.gson.annotations.SerializedName

data class ProdukKos(
    var code: Int?,
    @SerializedName("data")
    val datakos: List<Data> = arrayListOf(),
    val datagambar: List<ImageKost> = arrayListOf(),
//    var `data`: List<Data?>?,
    var message: String?
)