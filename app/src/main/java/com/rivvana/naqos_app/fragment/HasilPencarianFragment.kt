package com.rivvana.naqos_app.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.pencarian.DataPencarian
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentHasilPencarianBinding
import com.rivvana.naqos_app.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilPencarianFragment : Fragment() {
    private var _binding : FragmentHasilPencarianBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHasilPencarianBinding.inflate(inflater, container, false)
        binding.layoutToolbar.tvToolbar.text = "Hasil Pencarian"
        sessionManager = context?.let { SessionManager(it) }!!

        getHasil()

        return binding.root
    }

    private fun getHasil() {
        ApiConfig.instanceRetrofit.getCariKos(
            search = "Jakarta"
        ).enqueue(object : Callback<DataPencarian>{
            override fun onResponse(call: Call<DataPencarian>, response: Response<DataPencarian>) {
                Log.d("HASIL PENCARIAN", response.body().toString())
            }

            override fun onFailure(call: Call<DataPencarian>, t: Throwable) {

            }

        })
    }

}