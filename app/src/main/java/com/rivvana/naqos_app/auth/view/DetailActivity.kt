package com.rivvana.naqos_app.auth.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.rivvana.naqos_app.databinding.ActivityDetailBinding
import com.rivvana.naqos_app.model.Produk

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getInfo()
    }

    private fun getInfo() {
        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Produk>(data, Produk::class.java)
        //set img
        //val img = "https://be-naqos.up.railway.app/api/"+data[position].image
//        Picasso.get()
//            .load(img)
//            .placeholder(R.drawable.dummy_rekomendasi_kos1)
//            .error(R.drawable.dummy_rekomendasi_kos1)
//            .into(imgKos)
    }
}