package com.rivvana.naqos_app.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivvana.naqos_app.adapter.AdapterWishlist
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.wishlist.DataWishlist
import com.rivvana.naqos_app.auth.model.wishlist.WishlistRespons
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentWishlistBinding
import com.rivvana.naqos_app.viewmodel.WishlistViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    val binding get() = _binding!!
    lateinit var sessionManager: SessionManager
    lateinit var listWishlist: List<DataWishlist>

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
        ).enqueue(object : Callback<WishlistRespons>{
            override fun onResponse(
                call: Call<WishlistRespons>,
                response: Response<WishlistRespons>
            ) {
                val res = response.body()
                if (res!=null && sessionManager.getStatusLogin()){
                    listWishlist = res.data
                    binding.imgHouse.visibility = View.GONE
                    binding.tvHeader.visibility = View.GONE
                    binding.tvContent.visibility = View.GONE
                    displayWishlist()
                    Log.d("RESPON GET LIST", response.body().toString())
                }
                else {
                    binding.rvWishlist.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<WishlistRespons>, t: Throwable) {
                Log.d("ERROR WISHLIST", t.message.toString())
            }

        })
    }

    private fun displayWishlist() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvWishlist.adapter = AdapterWishlist(sessionManager, requireActivity(), listWishlist)
        binding.rvWishlist.layoutManager = layoutManager

    }

    companion object {

    }
}