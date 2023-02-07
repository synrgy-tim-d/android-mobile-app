package com.rivvana.naqos_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.adapter.AdapterWishlist
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.wishlist.Data
import com.rivvana.naqos_app.auth.model.wishlist.WishlistRespons
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentWishlistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager
    lateinit var listWishlist: List<Data>

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
                if (res==null){
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(R.id.container, WishlistEmpty())
                    transaction?.commit()
                } else {
                    listWishlist = res.data
                    displayWishlist()
                }
            }

            override fun onFailure(call: Call<WishlistRespons>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun displayWishlist() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvWishlist.adapter = AdapterWishlist(requireContext(), listWishlist)
        binding.rvWishlist.layoutManager = layoutManager

    }

    companion object {

    }
}