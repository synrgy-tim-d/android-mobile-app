package com.rivvana.naqos_app.auth.app

import com.rivvana.naqos_app.auth.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user-register/register")
    fun register(
        @Body register: RegisterRequest
   ): Call<RegisterResponse>

    @POST("user-login/login")
    fun login(
        @Body login: LoginRequest
    ): Call<LoginResponse>
}