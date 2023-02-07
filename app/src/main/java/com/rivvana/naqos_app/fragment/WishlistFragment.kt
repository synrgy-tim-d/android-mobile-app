package com.rivvana.naqos_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.WishlistResponse
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentWishlistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        binding.layoutToolbar.tvToolbar.text = "Wishlist"
        sessionManager = context?.let { SessionManager(it) }!!
        getWishlist()
        return binding.root
    }

    private fun getWishlist() {
        ApiConfig.instanceRetrofit.getWishlist(token = "Bearer ${sessionManager.fetchAuthToken()}"
        ).enqueue(object : Callback<WishlistResponse>{
            override fun onResponse(
                call: Call<WishlistResponse>,
                response: Response<WishlistResponse>
            ) {
                Toast.makeText(context, "GET DATA", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<WishlistResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    companion object {

    }
}