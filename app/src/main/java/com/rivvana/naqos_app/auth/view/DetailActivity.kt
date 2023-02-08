package com.rivvana.naqos_app.auth.view

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.WishlistReq
import com.rivvana.naqos_app.auth.model.WishlistResponse
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.components.DialogInputFragment
import com.rivvana.naqos_app.databinding.ActivityDetailBinding
import com.rivvana.naqos_app.model.Data
import com.rivvana.naqos_app.model.ImageKost
import com.squareup.picasso.Picasso
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
        buttonManager()
        getInfo()
    }

    private fun buttonManager() {
        btnSave()
        btnSewa()
    }


    private fun btnSave() {
        binding.btnSave.setOnClickListener{
            insert()
        }
    }

    fun insert(){

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
            val showInputDate = DialogInputFragment()
            showInputDate.show((this as AppCompatActivity).supportFragmentManager, "showInputDate")
        }
    }

    private fun getInfo() {
        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Data>(data, Data::class.java)
        Log.d("RESPON PRODUK", produk.toString())

        val imgKos = findViewById<ImageView>(R.id.img_kos_detail)
        val imgPKos = findViewById<ImageView>(R.id.img_profile)
        binding.tvNama.text = produk.name
        binding.tvAddress.text = produk.address
        binding.tvRate.text = produk.kostRating.toString()
        binding.tvLoc.text = produk.city?.city
        binding.tvNamaPemilik.text = produk.ownerId?.fullname
        binding.tvNamaKos.text = "Pemilik "+produk.name
        binding.btnWa.text = produk.ownerId?.phoneNumber
        binding.tvHarga.text = produk.rooms?.pricePerMonthly.toString()

        //set img kos
        val img = produk.imageKosts[0].url
        Picasso.get()
            .load(img)
            .placeholder(R.drawable.dummy_rekomendasi_kos1)
            .error(R.drawable.dummy_rekomendasi_kos1)
            .into(imgKos)

        //set image profile
        val imgProfile = produk.ownerId?.imgUrl
        Picasso.get()
            .load(imgProfile)
            .placeholder(R.drawable.dummy_photo_profil)
            .error(R.drawable.dummy_photo_profil)
            .into(imgPKos)
    }

}