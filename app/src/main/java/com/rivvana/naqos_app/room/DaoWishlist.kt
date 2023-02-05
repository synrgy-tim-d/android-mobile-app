package com.rivvana.naqos_app.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.rivvana.naqos_app.model.WishlistModel

interface DaoWishlist {
    @Insert(onConflict = REPLACE)
    fun insert(data: WishlistModel)

    @Delete
    fun delete(data: WishlistModel)

    @Update
    fun update(data: WishlistModel): Int

    @Query("SELECT * from wishlist ORDER BY id ASC")
    fun getAll(): List<WishlistModel>

    @Query("SELECT * FROM wishlist WHERE id = :id LIMIT 1")
    fun getNote(id: Int): WishlistModel

    @Query("DELETE FROM note")
    fun deleteAll(): Int
}