package com.rivvana.naqos_app.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutToolbar.btnBackLogin.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding.btnMasukAkunLogin.setOnClickListener{
            doLogin()
        }

        binding.tvDaftar.setOnClickListener{
            doSignUp()
        }

    }

    private fun doSignUp() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }


    fun doLogin(){
        startActivity(Intent(this, MainActivity::class.java))
    }




}