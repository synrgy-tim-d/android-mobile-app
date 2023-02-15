package com.rivvana.naqos_app.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.RegisterResponse
import com.rivvana.naqos_app.auth.model.UpdateDataRequest
import com.rivvana.naqos_app.auth.model.UserResponse
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentEditProfilBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfilFragment : Fragment() {
    private var _binding : FragmentEditProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfilBinding.inflate(inflater, container, false)
        binding.layoutToolbar.tvToolbar.text = "Edit Profile"
        sessionManager = context?.let { SessionManager(it) }!!
        fetchUser()
        backbtn()
        savebtn()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun savebtn() {
        binding.btnSave.setOnClickListener {
            val update = UpdateDataRequest(
                binding.etNamaLengkap.text.toString(),
                binding.etNomorHp.text.toString()
            )
            ApiConfig.instanceRetrofit.updateUser(
                update, token = "Bearer ${sessionManager.fetchAuthToken()}"
            ).enqueue(object : Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    Log.d("UBAH DATA", response.body().toString())
                    showDialog()
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("UBAH DATA", t.message.toString() )
                }
            })
        }
    }

    private fun showDialog() {
        val sDialog = AlertDialog.Builder(context)
        sDialog.setTitle("Data Berhasil diubah")
        sDialog.setIcon(R.drawable.img_otp_success)
        sDialog.setPositiveButton("Ok"){
            sDialog, id ->
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        val alertDialog = sDialog.create()
        alertDialog.show()

    }

    private fun backbtn() {
        binding.layoutToolbar.btnBackToolbar.setOnClickListener{
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchUser() {
        ApiConfig.instanceRetrofit.getUser(token = "Bearer ${sessionManager.fetchAuthToken()}"
        ).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val userRespon = response.body()
                val userResponError = response.errorBody()
                if (userRespon!=null){
                    sessionManager.setUser(userRespon.data)
                    if (sessionManager.getUser()!=null){
                        Log.d("RESPON USER BERHASIL", response.body().toString())
                        val user = sessionManager.getUser()
                        binding.etNamaLengkap.hint = user?.fullname
                        binding.etNomorHp.hint = user?.phoneNumber
                    }
                }
                else {
                    Log.d("RESPON USER GAGAL", userResponError.toString())
                }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("RESPON USER ERROR", t.message.toString())
            }
        })
    }

}