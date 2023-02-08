package com.rivvana.naqos_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivvana.naqos_app.auth.app.ApiService
import com.rivvana.naqos_app.auth.model.Data
import com.rivvana.naqos_app.room.KosDatabase

class KosRepository (
    private val apiService: ApiService,
    private val kosDatabase: KosDatabase)
{
    private val kosLiveData = MutableLiveData<Data>()

    val datakos : LiveData<Data>
        get() = kosLiveData

    suspend fun getDataKos(){
        val result = apiService.getProduk()

    }
}