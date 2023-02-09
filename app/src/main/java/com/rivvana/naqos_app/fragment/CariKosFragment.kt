package com.rivvana.naqos_app.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.adapter.AdapterCity
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.view.LoginActivity
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentCariKosBinding
import com.rivvana.naqos_app.model.AllDataCity
import com.rivvana.naqos_app.model.DataX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CariKosFragment : Fragment() {
    var _binding: FragmentCariKosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCariKosBinding.inflate(inflater, container, false)

        btnBack()
        return binding.root
    }


    private fun btnBack() {
        binding.btnBack.setOnClickListener{
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

}