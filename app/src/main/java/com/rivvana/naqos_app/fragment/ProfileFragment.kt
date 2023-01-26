package com.rivvana.naqos_app.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rivvana.naqos_app.auth.view.LoginActivity
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.layoutToolbar.tvToolbar.text = "Profile"
        sessionManager = context?.let { SessionManager(it) }!!
        binding.layoutToolbar.btnBackToolbar.setOnClickListener{
            Toast.makeText(context, "Button Back", Toast.LENGTH_SHORT).show()
        }

        binding.tvLogout.setOnClickListener{
            sessionManager.removeToken()
            startActivity(Intent(context, LoginActivity::class.java))
        }

        return binding.root
    }

    companion object {

    }
}