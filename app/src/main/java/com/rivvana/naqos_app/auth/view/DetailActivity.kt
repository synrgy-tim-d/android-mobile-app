package com.rivvana.naqos_app.auth.view

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.WishlistReq
import com.rivvana.naqos_app.auth.model.WishlistResponse
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.ActivityDetailBinding
import com.rivvana.naqos_app.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        btnSave()
        btnSewa()
        getInfo()
    }

    private fun btnSave() {
        binding.btnSave.setOnClickListener{
            insert()
        }
    }

    fun insert(){
//        val myDb: MyDatabase = MyDatabase.getInstance(this)!! // call database
//        val wishlist = WishlistModel() //create new note
//        wishlist.name = "First Note"
//
//        CompositeDisposable().add(Observable.fromCallable { myDb.daoWishlist().insert(wishlist) }
//            .subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d("RESPON ADD", "DATA MASUK")
//            })

        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Data>(data, Data::class.java)
        val wishlist = WishlistReq(
            produk.id.toString()
        )


        ApiConfig.instanceRetrofit.addWishlist(wishlist,token = "Bearer ${sessionManager.fetchAuthToken()}"
        ).enqueue(object : Callback<WishlistResponse>{
            override fun onResponse(
                call: Call<WishlistResponse>,
                response: Response<WishlistResponse>
            ) {
                val respon = response.body()
                val responError = response.errorBody()
                if (respon!=null){
                    Log.d("GET WISHLIST", respon.toString())
                    Toast.makeText(this@DetailActivity, "${response.body().toString()}", Toast.LENGTH_SHORT).show()
                }else {
                    Log.d("ERROR WISHLIST", responError.toString())
                    Toast.makeText(this@DetailActivity, "Error "+responError.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WishlistResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun btnSewa() {
        binding.btnSewa.setOnClickListener{

        }
    }

    private fun getInfo() {
        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Data>(data, Data::class.java)
        Log.d("RESPON PRODUK", produk.toString())
//        binding.imgKosDetail
        binding.tvNama.text = produk.name
        binding.tvAddress.text = produk.address
//        binding.tvRate.text = produk.rate
        binding.tvLoc.text = produk.city?.city
//        binding.imgProfile
        binding.tvNamaPemilik.text = produk.ownerId?.fullname
        binding.tvNamaKos.text = "Pemilik "+produk.name
        binding.btnWa.text = produk.ownerId?.phoneNumber

        //set img
        //val img = "https://be-naqos.up.railway.app/api/"+data[position].image
//        Picasso.get()
//            .load(img)
//            .placeholder(R.drawable.dummy_rekomendasi_kos1)
//            .error(R.drawable.dummy_rekomendasi_kos1)
//            .into(imgKos)
    }

}