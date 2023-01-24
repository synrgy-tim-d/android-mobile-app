package com.rivvana.naqos_app.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.app.SessionManager
import com.rivvana.naqos_app.auth.model.LoginRequest
import com.rivvana.naqos_app.auth.model.LoginResponse
import com.rivvana.naqos_app.databinding.ActivityLoginBinding
import retrofit2.Callback

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

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


    private fun doLogin() {
        //validasi email
        if (binding.etEmailLogin.text.isEmpty()) {
            binding.etEmailLogin.error = "Kolom Email tidak boleh kosong"
            binding.etEmailLogin.requestFocus()
            return
        }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmailLogin.text).matches()){
            binding.etEmailLogin.error = "Email tidak valid"
            binding.etEmailLogin.requestFocus()
            return
        }
        //validasi pass
        else if (binding.etPasswordLogin.text!!.isEmpty()) {
            binding.etPasswordLogin.error = "Kolom Password tidak boleh kosong"
            binding.etPasswordLogin.requestFocus()
            return
        } else if (binding.etPasswordLogin.text!!.length < 6){
            binding.etPasswordLogin.error = "Password minimal 6 karakter"
            binding.etPasswordLogin.requestFocus()
            return
        }

        val login = LoginRequest(
            binding.etEmailLogin.text.toString(),
            binding.etPasswordLogin.text.toString()
        )

        ApiConfig.instanceRetrofit.login(
            login).enqueue(object : Callback<LoginResponse> {

            override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<LoginResponse>, response: retrofit2.Response<LoginResponse>) {
                val loginRespon = response.body()!!
                if (!loginRespon.access_token.isNullOrBlank()){
                    Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                    sessionManager.saveAuthToken(loginRespon.access_token)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }else {
                    Toast.makeText(this@LoginActivity, "Error "+loginRespon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}