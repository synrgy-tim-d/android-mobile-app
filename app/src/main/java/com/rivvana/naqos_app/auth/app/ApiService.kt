package com.rivvana.naqos_app.auth.app

import com.rivvana.naqos_app.auth.model.Login
import com.rivvana.naqos_app.auth.model.Register
import com.rivvana.naqos_app.auth.viewmodel.ResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user-register/register")
    fun register(
        @Body register: Register
   ): Call<ResponseModel>

    @POST("user-login/login")
    fun login(
        @Body login: Login
    ): Call<ResponseModel>
}