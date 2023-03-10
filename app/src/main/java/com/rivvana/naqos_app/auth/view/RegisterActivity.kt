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
import com.rivvana.naqos_app.auth.model.RegisterRequest
import com.rivvana.naqos_app.auth.model.RegisterResponse
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

        setToolbar()

        binding.btnDaftarAkun.setOnClickListener{
            signUp()
        }

        binding.btnDaftarAkunGoogle.setOnClickListener{
            signUpWithGoogle()
        }

        binding.tvMasuk.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setToolbar() {
        binding.layoutToolbar.tvToolbar.text = "Daftar"
        binding.layoutToolbar.btnBackToolbar.setOnClickListener{
            Log.d("LAYOUT BACK", "onBackPressed Called")
            val setIntent = Intent(Intent(this, MainActivity::class.java))
            setIntent.addCategory(Intent.CATEGORY_HOME)
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(setIntent)
        }
    }

    private fun signUpWithGoogle() {
        Toast.makeText(this,"Daftar dengan Akun Google", Toast.LENGTH_SHORT).show()
    }

    private fun signUp() {
        //validasi nama
        if (binding.etFullname.text.isEmpty()) {
            binding.etFullname.error = "Kolom Nama tidak boleh kosong"
            binding.etFullname.requestFocus()
            return
        } else if(binding.etFullname.text.contains("[0-9]".toRegex())) {
            binding.etFullname.error = "Nama tidak boleh mengandung angka"
            binding.etFullname.requestFocus()
            return
        } else if (!binding.etFullname.text.contains("[A-Za-z][^.]*".toRegex())){
            binding.etFullname.error = "Nama tidak boleh mengandung simbol"
            binding.etFullname.requestFocus()
            return
        }
        //validasi email
        else if (binding.etEmailRegister.text.isEmpty()) {
            binding.etEmailRegister.error = "Kolom Email tidak boleh kosong"
            binding.etEmailRegister.requestFocus()
            return
        }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmailRegister.text).matches()){
            binding.etEmailRegister.error = "Email tidak valid"
            binding.etEmailRegister.requestFocus()
            return
        }
        //validasi hp
        else if (binding.etPhone.text.isEmpty()) {
            binding.etPhone.error = "Kolom Nomor Telepon tidak boleh kosong"
            binding.etPhone.requestFocus()
            return
        }else if (binding.etPhone.text.contains("[a-z]".toRegex())){
            binding.etPhone.error = "Nomor Telepon tidak valid"
            binding.etPhone.requestFocus()
            return
        }else if (binding.etPhone.text!!.length < 10 || binding.etPhone.text!!.length > 13 ) {
            binding.etPhone.error = "Nomor Telepon tidak valid"
            binding.etPhone.requestFocus()
            return
        }
        //validasi pass
        else if (binding.etPasswordRegister.text!!.isEmpty()) {
            binding.etPasswordRegister.error = "Kolom Password tidak boleh kosong"
            binding.etPasswordRegister.requestFocus()
            return
        } else if (binding.etPasswordRegister.text!!.length < 6){
            binding.etPasswordRegister.error = "Password minimal 6 karakter"
            binding.etPasswordRegister.requestFocus()
            return
        }

        val register = RegisterRequest(
            binding.etFullname.text.toString(),
            binding.etPhone.text.toString(),
            binding.etEmailRegister.text.toString(),
            binding.etPasswordRegister.text.toString(),
            "PENYEWA"
        )

        binding.animationView.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(
        register).enqueue(object : Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                binding.animationView.visibility = View.GONE
                Toast.makeText(this@RegisterActivity, "Error"+t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                binding.animationView.visibility = View.GONE
                val respon = response.body()
                val responError = response.errorBody()
                if (respon!=null){
                    Toast.makeText(this@RegisterActivity, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity, OtpActivity::class.java))
                    finish()
                }else {
                    Toast.makeText(this@RegisterActivity, "Error "+responError.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}