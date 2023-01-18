package com.rivvana.naqos_app.auth.app

import com.rivvana.naqos_app.auth.model.ResponseModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class Register (
        val username: String,
        val phoneNumber: String,
        val fullname: String,
        val password: String
        )

interface ApiService {
    @POST("user-register/register")
    fun register(
        @Body register: Register
   ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("user-login/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("user-register/send-otp")
    fun sendOtp(
        @Field("email") email:String,
    ): Call<ResponseBody>
}