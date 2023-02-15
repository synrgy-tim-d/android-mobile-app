package com.rivvana.naqos_app.auth.app

import com.rivvana.naqos_app.auth.model.*
import com.rivvana.naqos_app.auth.model.pencarian.DataPencarian
import com.rivvana.naqos_app.auth.model.statuswishlist.Status
import com.rivvana.naqos_app.auth.model.wishlist.WishlistRespons
import com.rivvana.naqos_app.model.AllDataCity
import com.rivvana.naqos_app.model.ProdukKos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

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

    @PUT("users/update_data")
    fun updateUser(
        @Body updateData: UpdateDataRequest,
        @Header("Authorization") token: String,
    ): Call<RegisterResponse>

    @GET("public/kost")
    fun getProduk():Call<ProdukKos>

    @GET("public/kost?")
    fun getCariKos(
        @Query("search", encoded = true) search: String,
    ):Call<DataPencarian>

    @POST("auth/send-otp")
    fun otpRequest(
        @Body otpReq: OtpRequest
    ): Call<OtpResponse>

    @POST("wishlists/add")
    fun addWishlist(@Body addWishlist: WishlistReq, @Header("Authorization") token: String): Call<WishlistResponse>

    @GET("wishlists/status?")
    fun checkStatusWishlist(
        @Query("kostId") kostId : String,
        @Header("Authorization") token: String
    ):Call<Status>

    @DELETE("wishlists/destroy?")
    fun deleteWishlist(
        @Query("kostId") kostId : String,
        @Header("Authorization") token: String
    ):Call<Status>

    @GET("wishlists/get")
    fun getWishlist(@Header("Authorization") token: String):Call<WishlistRespons>

    @GET("cities")
    fun getCity():Call<AllDataCity>


}