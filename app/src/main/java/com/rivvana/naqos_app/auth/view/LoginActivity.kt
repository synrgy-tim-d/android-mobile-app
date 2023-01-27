package com.rivvana.naqos_app.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.auth.model.LoginRequest
import com.rivvana.naqos_app.auth.model.LoginResponse
import com.rivvana.naqos_app.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.layoutToolbar.tvToolbar.text = "Masuk"
        binding.layoutToolbar.btnBackToolbar.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding.btnMasukAkunLogin.setOnClickListener{
            doLogin()
        }

        binding.btnMasukAkunGoogle.setOnClickListener{
            Toast.makeText(this,"Daftar dengan Akun Google", Toast.LENGTH_SHORT).show()
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

        binding.animationView.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(
            login).enqueue(object : Callback<LoginResponse> {

//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                var loginRespon = response.body()
//                if (response.body()!=null){
//                    response.body()?.string()?.let { Log.d("RESPON", it) }
//                    sessionManager.saveAuthToken(loginRespon.)
//                } else {
//                    response.errorBody()?.string()?.let { Log.d("RESPON ERROR", it) }
//                    if (response.errorBody()?.string().toString().contains("\"status\":500")){
//                        Toast.makeText(this@LoginActivity, "User not Found", Toast.LENGTH_SHORT).show()
//                    }
//                    if (response.errorBody()?.string().toString().contains("\"code\":404")){
//                        Toast.makeText(this@LoginActivity, "User not Found", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.d("RESPON", t.message.toString())
//            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.animationView.visibility = View.GONE
                val loginRespon = response.body()
                val loginError = response.errorBody()
                if (response.body()!=null){
                    Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                    loginRespon?.data?.access_token.toString().let { Log.d("RESPON BERHASIL", it) }
                    sessionManager.saveAuthToken(loginRespon?.data?.access_token)

                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                else {
                    loginError?.string()?.let { Log.d("RESPON GAGAL", it) }
                    Toast.makeText(this@LoginActivity, "Akun belum terdaftar", Toast.LENGTH_SHORT).show()
                    response.errorBody()?.string()?.let { Log.d("RESPON ERROR", it) }
                    if (response.errorBody()?.string().toString().contains("\"status\":500")){
                        Toast.makeText(this@LoginActivity, "User not Found", Toast.LENGTH_SHORT).show()
                    }
                    if (response.errorBody()?.string().toString().contains("\"code\":404")){
                        Toast.makeText(this@LoginActivity, "User not Found", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.animationView.visibility = View.GONE
                Log.d("RESPON ERROR", t.message.toString())
            }
        })
    }
}