package com.rivvana.naqos_app.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.Login
import com.rivvana.naqos_app.auth.viewmodel.ResponseModel
import com.rivvana.naqos_app.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val login = Login(
            binding.etEmailLogin.text.toString(),
            binding.etPasswordLogin.text.toString()
        )

        ApiConfig.instanceRetrofit.login(
            login).enqueue(object : Callback<ResponseModel> {
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error"+t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val respon = response.body()!!
                if (respon.access_token != null){
                    Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this@RegisterActivity, OtpActivity::class.java))
//                    finish()
                }else {
                    Toast.makeText(this@LoginActivity, "Error "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}