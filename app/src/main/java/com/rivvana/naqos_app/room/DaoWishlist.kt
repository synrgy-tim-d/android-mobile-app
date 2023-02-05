package com.rivvana.naqos_app.room

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.rivvana.naqos_app.model.WishlistModel

@Dao
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
    fun getWishlist(id: Int): WishlistModel

    @Query("DELETE FROM wishlist")
    fun deleteAll(): Int
}