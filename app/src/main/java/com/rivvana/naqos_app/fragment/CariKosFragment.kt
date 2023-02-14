package com.rivvana.naqos_app.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.databinding.FragmentCariKosBinding

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
        btnManager()
        return binding.root
    }

    private fun btnManager() {
        binding.btnJakarta.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, HasilPencarianFragment())
            transaction?.commit()
        }
    }


    private fun btnBack() {
        binding.btnBack.setOnClickListener{
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

}