package com.rivvana.naqos_app.auth.view

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.rivvana.naqos_app.databinding.ActivityDetailBinding
import com.rivvana.naqos_app.model.Data

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnSewa()
        getInfo()
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