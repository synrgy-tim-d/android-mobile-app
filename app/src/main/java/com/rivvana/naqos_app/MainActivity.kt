package com.rivvana.naqos_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivvana.naqos_app.auth.app.SessionManager
import com.rivvana.naqos_app.auth.view.LoginActivity
import com.rivvana.naqos_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnLogout.setOnClickListener {
            sessionManager.removeToken()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    override fun onStart() {
        super.onStart()
        if (sessionManager.fetchAuthToken().isNullOrBlank()){ //sp.fetchToken.isEmpty
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


}