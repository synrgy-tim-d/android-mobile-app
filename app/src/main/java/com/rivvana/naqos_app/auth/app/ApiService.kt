package com.rivvana.naqos_app.auth.app

import com.rivvana.naqos_app.auth.model.*
import com.rivvana.naqos_app.auth.model.wishlist.WishlistRespons
import com.rivvana.naqos_app.model.AllDataCity
import com.rivvana.naqos_app.model.ProdukKos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    fun register(
        @Body register: RegisterRequest
   ): Call<RegisterResponse>

    @POST("auth/login")
    fun login(
        @Body login: LoginRequest
    ): Call<LoginResponse>

    @GET("users/get")
    fun getUser(@Header("Authorization") token: String):Call<UserResponse>

    @GET("public/kost")
    fun getProduk():Call<ProdukKos>

    @POST("auth/send-otp")
    fun otpRequest(
        @Body otpReq: OtpRequest
    ): Call<OtpResponse>

    @POST("wishlists/add")
    fun addWishlist(@Body addWishlist: WishlistReq, @Header("Authorization") token: String): Call<WishlistResponse>

    @GET("wishlists/get")
    fun getWishlist(@Header("Authorization") token: String):Call<WishlistRespons>

    @GET("cities")
    fun getCity():Call<AllDataCity>


}