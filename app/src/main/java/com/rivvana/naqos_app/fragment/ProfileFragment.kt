package com.rivvana.naqos_app.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.rivvana.naqos_app.R
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

        setDataUser()

        binding.layoutToolbar.btnBackToolbar.setOnClickListener{
            Toast.makeText(context, "Button Back", Toast.LENGTH_SHORT).show()
        }

        binding.tvLogout.setOnClickListener{
            showDialog()
        }

        return binding.root
    }

    private fun setDataUser() {

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
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    companion object {

    }
}