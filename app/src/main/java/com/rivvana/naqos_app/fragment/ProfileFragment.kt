package com.rivvana.naqos_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.layoutToolbar.tvToolbar.text = "Profile"

        binding.layoutToolbar.btnBackToolbar.setOnClickListener{
            Toast.makeText(context, "Button Back", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    companion object {

    }
}