package com.rivvana.naqos_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivvana.naqos_app.auth.helper.SharedPref
import com.rivvana.naqos_app.auth.view.LoginActivity
import com.rivvana.naqos_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sp : SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = SharedPref(this)

        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        if (!sp.getStatusLogin()){ //sp.fetchToken.isEmpty
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


}