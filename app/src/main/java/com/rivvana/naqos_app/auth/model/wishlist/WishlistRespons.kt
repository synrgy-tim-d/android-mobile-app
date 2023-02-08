package com.rivvana.naqos_app.auth.model.wishlist

data class WishlistRespons(
    val currentPage: Int,
    val data: List<DataWishlist>,
    val first: Boolean,
    val last: Boolean,
    val size: Int,
    val totalElement: Int,
    val totalPage: Int
)