package com.rivvana.naqos_app.auth.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivvana.naqos_app.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getInfo()
    }

    private fun getInfo() {
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val desc = intent.getStringExtra("desc")

        //set value
        binding.tvNama.text = name
        binding.tvLoc.text = address
        binding.tvDesc.text = desc
    }
}