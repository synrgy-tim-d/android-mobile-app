package com.rivvana.naqos_app.room

import androidx.room.*
import com.rivvana.naqos_app.auth.model.Data

@Dao
interface RoomDao {
    @Insert
    suspend fun insertDataKos(data : List<Data>)

    @Query ("SELECT * FROM data")
    suspend fun getDataKos() : List<Data>
}