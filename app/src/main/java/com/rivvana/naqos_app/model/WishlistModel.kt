package com.rivvana.naqos_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist") // the name of tabel
class WishlistModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    lateinit var id_kost:String
    lateinit var img:String
    lateinit var name: String
    lateinit var description: String
    lateinit var rate: String
    lateinit var city: String
    var price: Int=0
}