package com.rivvana.naqos_app.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.databinding.ActivityRegisterBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutToolbar.btnBackRegister.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnDaftarAkun.setOnClickListener{
            signUp()
        }
    }

    private fun signUp() {
        ApiConfig.instanceRetrofit.register(binding.etEmailRegister.text.toString(),
        binding.etFullname.text.toString(),
        binding.etPasswordRegister.text.toString()).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}