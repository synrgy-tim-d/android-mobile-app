package com.rivvana.naqos_app.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivvana.naqos_app.MainActivity
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.adapter.AdapterPencarian
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.Data
import com.rivvana.naqos_app.auth.model.pencarian.DataP
import com.rivvana.naqos_app.auth.model.pencarian.DataPencarian
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.databinding.FragmentHasilPencarianBinding
import com.rivvana.naqos_app.model.ProdukKos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilPencarianFragment : Fragment() {
    private var _binding : FragmentHasilPencarianBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager
    lateinit var listPencarian: List<com.rivvana.naqos_app.model.Data>
    var adapterPencarian: AdapterPencarian? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHasilPencarianBinding.inflate(inflater, container, false)
        binding.layoutToolbar.tvToolbar.text = "Hasil Pencarian"
        sessionManager = context?.let { SessionManager(it) }!!
        btnBack()
        getHasil()
        var jml = adapterPencarian?.itemCount.toString()
        var hasil = view?.findViewById<TextView>(R.id.tv_hasil)
        hasil?.text = "Menampilkan ${jml} hasil pencarian di Jakarta"

        return binding.root
    }

    private fun btnBack() {
        binding.layoutToolbar.btnBackToolbar.setOnClickListener{
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getHasil() {
        val pencarian = "%5B%22Jakarta%22%5D"
        ApiConfig.instanceRetrofit.getCariKos(
            pencarian,
            fields = "%5B%22city.city%22%5D"
        ).enqueue(object : Callback<ProdukKos>{
            override fun onResponse(call: Call<ProdukKos>, response: Response<ProdukKos>) {
                val res = response.body()
                if (res!=null){
                    listPencarian = res.datakos
                    displayData()
                    Log.d("PENCARIAN", response.body().toString())
//                    Log.d("PENCARIAN", listPencarian.toString())
                }
                else {
                    Log.d("PENCARIAN", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ProdukKos>, t: Throwable) {
                Log.d("PENCARIAN", t.message.toString())
            }

        })
    }

    private fun displayData() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvHasilPencarian.adapter = AdapterPencarian(requireActivity(), listPencarian)
        binding.rvHasilPencarian.layoutManager = layoutManager
    }

}