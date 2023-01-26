package com.rivvana.naqos_app.auth.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivvana.naqos_app.databinding.ActivityOtpSuccessBinding

class OtpSuccessActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpSuccessBinding
    private var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        uri = intent.data

        binding.layoutToolbar.tvToolbar.text = "Verifikasi"
        binding.btnBackLogin.setOnClickListener{
            backLogin()
        }

    }

    private fun backLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}