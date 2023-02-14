package com.rivvana.naqos_app.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.adapter.AdapterProduk
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.model.Data
import com.rivvana.naqos_app.model.ImageKost
import com.rivvana.naqos_app.model.ProdukKos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    lateinit var rvRekomendasi: RecyclerView
    lateinit var rvKosMurah: RecyclerView

    lateinit var spinnerRekomendasi: Spinner
    lateinit var spinnerKosMurah: Spinner
    lateinit var listProduk: List<Data>
    lateinit var setKota: String
    lateinit var btnCari:TextView

    val arrSpinerRekomendasi = arrayOf("Jakarta", "Bandung", "Surabaya", "Semarang", "Yogyakarta")
    val arrSpinerKosMurah= arrayOf("Jakarta", "Bandung", "Surabaya", "Semarang", "Yogyakarta")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        init(view)
        btnCariKos()
        setSpinner()
        showKos()
        return view
    }

    private fun showKos() {
        ApiConfig.instanceRetrofit.getProduk(
        ).enqueue(object : Callback<ProdukKos>{
            override fun onResponse(call: Call<ProdukKos>, response: Response<ProdukKos>) {
                val res = response.body()
                if (res!=null){
                    listProduk = res.datakos
                    displayProduk()
                    Log.d("RESPON GET LIST", listProduk.toString())
                }else{
                    Log.d("RESPON GET GAGAL", response.errorBody()!!.string())
                }

            }

            override fun onFailure(call: Call<ProdukKos>, t: Throwable) {
                Log.d("ERROR DASHBOARD", t.message.toString())
            }

        })
    }

    private fun setSpinner() {
        val arrayAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrSpinerRekomendasi)
        val arrayAdapter2 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrSpinerKosMurah)

        spinnerRekomendasi.adapter = arrayAdapter
        spinnerKosMurah.adapter = arrayAdapter2
        spinnerRekomendasi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ){
                if (position == 1){
                    setKota = "Jakarta"
                }else if (position == 2){
                    setKota = "Bandung"
                }else if (position == 3){
                    setKota = "Surabaya"
                }else if (position == 4){
                    setKota = "Semarang"
                }else{
                    setKota = "Yogyakarta"
                }

//                Toast.makeText(activity, "Daftar rekomendasi di "+arrSpinerRekomendasi[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        spinnerKosMurah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ){
//                Toast.makeText(activity, "Daftar kos murah di "+arrSpinerRekomendasi[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

    }

    private fun btnCariKos() {
        btnCari.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, CariKosFragment())
            transaction?.commit()
        }
    }

    private fun displayProduk() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        rvRekomendasi.adapter = AdapterProduk(requireContext(), listProduk)
        rvRekomendasi.layoutManager = layoutManager

        rvKosMurah.adapter = AdapterProduk(requireContext(), listProduk)
        rvKosMurah.layoutManager = layoutManager2
    }


    private fun init(view: View) {
        btnCari = view.findViewById(R.id.et_cari_kos)
        rvRekomendasi = view.findViewById(R.id.rv_rekomendasi)
        rvKosMurah = view.findViewById(R.id.rv_kosmurah)
        spinnerRekomendasi = view.findViewById(R.id.spiner_rekomendasi)
        spinnerKosMurah = view.findViewById(R.id.spiner_kosmurah)
    }

}