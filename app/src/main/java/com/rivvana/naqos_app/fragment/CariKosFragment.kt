package com.rivvana.naqos_app.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
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
    lateinit var listCity: List<DataX>
    var _binding: FragmentCariKosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCariKosBinding.inflate(inflater, container, false)

        getDataCity()
        btnBack()
        return binding.root
    }

    private fun getDataCity() {
        ApiConfig.instanceRetrofit.getCity().enqueue(object :Callback<AllDataCity>{
            override fun onResponse(call: Call<AllDataCity>, response: Response<AllDataCity>) {
                val res = response.body()
                if (res!=null){
                    listCity = res.data
                    displayCity()
                    Log.d("RESPON GET BERHASIL", listCity.toString())
                }else{
                    Log.d("RESPON GET GAGAL", response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<AllDataCity>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun displayCity() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvCity.adapter = AdapterCity(requireActivity(), listCity)
        binding.rvCity.layoutManager = layoutManager
    }

    private fun btnBack() {
        binding.btnBack.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, SearchFragment())
            transaction?.commit()
        }
    }

}