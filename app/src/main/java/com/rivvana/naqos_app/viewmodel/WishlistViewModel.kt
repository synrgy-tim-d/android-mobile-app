package com.rivvana.naqos_app.viewmodel

import android.provider.ContactsContract.Data
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.wishlist.DataWishlist
import com.rivvana.naqos_app.auth.model.wishlist.WishlistRespons
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistViewModel : ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<DataWishlist>>
    lateinit var sessionManager: SessionManager
    lateinit var listWishlist: List<DataWishlist>
    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerDataObserve() : MutableLiveData<List<DataWishlist>>{
        return recyclerListData
    }

    fun apiCall(){
        ApiConfig.instanceRetrofit.getWishlist(token = "Bearer ${sessionManager.fetchAuthToken()}"
        ).enqueue(object : Callback<WishlistRespons> {
            override fun onResponse(
                call: Call<WishlistRespons>,
                response: Response<WishlistRespons>
            ) {
                val res = response.body()
                if (res!=null && sessionManager.getStatusLogin()){
                    listWishlist = res.data
                    recyclerListData.postValue(listWishlist)
                    Log.d("RESPON GET LIST", response.body().toString())
                }
            }

            override fun onFailure(call: Call<WishlistRespons>, t: Throwable) {
                Log.d("ERROR WISHLIST", t.message.toString())
            }

        })
    }
}