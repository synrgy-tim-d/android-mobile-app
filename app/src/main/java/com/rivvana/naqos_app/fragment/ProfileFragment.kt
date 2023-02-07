package com.rivvana.naqos_app.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.User
import com.rivvana.naqos_app.auth.model.UserResponse
import com.rivvana.naqos_app.auth.view.LoginActivity
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.layoutToolbar.tvToolbar.text = "Profile"
        sessionManager = context?.let { SessionManager(it) }!!
        //val token = sessionManager.fetchAuthToken()

        fetchUser()

        binding.tvLogout.setOnClickListener{
            showDialog()
        }

        return binding.root
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
                            binding.etEmail.text = user?.username
                            binding.etNamaLengkap.text = user?.fullname
                            binding.etNomorHP.text = user?.phoneNumber
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

    private fun showDialog() {
        val dialog = layoutInflater.inflate(R.layout.dialog_logout, null)

        val customDialog = AlertDialog.Builder(context)
            .setView(dialog)
            .show()

        val btnDismiss = dialog.findViewById<Button>(R.id.btDismissCustomDialog)
        btnDismiss.setOnClickListener{
            customDialog.dismiss()
        }

        val btnLogout = dialog.findViewById<Button>(R.id.btLogoutCustomDialog)
        btnLogout.setOnClickListener{
            sessionManager.removeToken()
            sessionManager.clearSession()
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    companion object {

    }
}