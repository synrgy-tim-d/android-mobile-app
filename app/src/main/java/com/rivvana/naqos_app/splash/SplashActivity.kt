package com.rivvana.naqos_app.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivLogoApp.alpha = 0f
        binding.ivLogoApp.animate().setDuration(3000).alpha(1f).withEndAction{
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}