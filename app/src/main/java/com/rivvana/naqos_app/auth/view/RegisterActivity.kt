package com.rivvana.naqos_app.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.app.Register
import com.rivvana.naqos_app.auth.viewmodel.ResponseModel
import com.rivvana.naqos_app.databinding.ActivityRegisterBinding
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
            sendOtp()
        }
    }

    private fun sendOtp() {
    }

    private fun signUp() {
        val register = Register(
            binding.etFullname.text.toString(),
            binding.etPhone.text.toString(),
            binding.etEmailRegister.text.toString(),
            binding.etPasswordRegister.text.toString()
        )
        ApiConfig.instanceRetrofit.register(
        register).enqueue(object : Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Error"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                Toast.makeText(this@RegisterActivity, "Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, OtpActivity::class.java))
                finish()
            }
        })
    }
}