package com.rivvana.naqos_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        binding.layoutToolbar.tvToolbar.text = "Wishlist"

        binding.layoutToolbar.btnBackToolbar.setOnClickListener{
            Toast.makeText(context, "Button Back", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    companion object {

    }
}