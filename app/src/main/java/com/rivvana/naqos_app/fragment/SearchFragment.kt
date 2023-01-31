package com.rivvana.naqos_app.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.adapter.AdapterProduk
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.ResponseModel
import com.rivvana.naqos_app.model.Produk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    lateinit var rvRekomendasi: RecyclerView
    lateinit var rvKosMurah: RecyclerView

    lateinit var spinnerRekomendasi: Spinner
    lateinit var spinnerKosMurah: Spinner

    val arrSpinerRekomendasi = arrayOf("Bekasi", "Jakarta", "Bandung", "Surabaya", "Tangerang", "Depok", "Semarang", "Bogor")
    val arrSpinerKosMurah= arrayOf("Bekasi", "Jakarta", "Bandung")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        init(view)
        getProduk()

        return view
    }

    private fun displayProduk() {

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
                Toast.makeText(activity, "Daftar rekomendasi di "+arrSpinerRekomendasi[position], Toast.LENGTH_SHORT).show()
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
                Toast.makeText(activity, "Daftar kos murah di "+arrSpinerRekomendasi[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        rvRekomendasi.adapter = AdapterProduk(requireActivity(), listProduk)
        rvRekomendasi.layoutManager = layoutManager

        rvKosMurah.adapter = AdapterProduk(requireActivity(), listProduk)
        rvKosMurah.layoutManager = layoutManager2
    }

    private var listProduk: ArrayList<Produk> = ArrayList()
    private fun getProduk() {

        ApiConfig.instanceRetrofit.getProduk().enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val res = response.body()
                if (res!=null){

                    Log.d("RESPON GET BERHASIL", response.body().toString())
                    listProduk = res.data
                    displayProduk()
                }else{
                    Log.d("RESPON GET GAGAL", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("RESPON GET ERROR", t.message.toString())
            }

        })
    }

    private fun init(view: View) {
        rvRekomendasi = view.findViewById(R.id.rv_rekomendasi)
        rvKosMurah = view.findViewById(R.id.rv_kosmurah)
        spinnerRekomendasi = view.findViewById(R.id.spiner_rekomendasi)
        spinnerKosMurah = view.findViewById(R.id.spiner_kosmurah)
    }

//    val arrRekomendasi: ArrayList<Rekomendasi> get() {
//            val arr = ArrayList<Rekomendasi>()
//            val p1 = Rekomendasi()
//            p1.gambar = R.drawable.dummy_rekomendasi_kos1
//            p1.nama = "Kos Bunga"
//            p1.deskripsi = "Kosan khusus perempuan dan wanita termurah di Bekasi"
//            p1.rate = "4.3"
//            p1.kota = "Bekasi"
//            p1.harga = "Rp.700.000/bln"
//
//            val p2 = Rekomendasi()
//            p2.gambar = R.drawable.dummy_rekomendasi_kos2
//            p2.nama = "Kos Elite"
//            p2.deskripsi = "Kosan murah dengan sarana elite dan kualitas terjamin"
//            p2.rate = "4.4"
//            p2.kota = "Bekasi"
//            p2.harga = "Rp.1.200.000/bln"
//
//            val p3 = Rekomendasi()
//            p3.gambar = R.drawable.dummy_rekomendasi_kos3
//            p3.nama = "Kos Wkwkw"
//            p3.deskripsi = "Kosan murah dengan sarana elite dan kualitas terjamin"
//            p3.rate = "4.1"
//            p3.kota = "Bekasi"
//            p3.harga = "Rp.2.000.000/bln"
//
//            arr.add(p1)
//            arr.add(p2)
//            arr.add(p3)
//
//            return arr
//        }
//
//    val arrKosMurah: ArrayList<Rekomendasi> get() {
//        val arr = ArrayList<Rekomendasi>()
//        val p1 = Rekomendasi()
//        p1.gambar = R.drawable.dummy_rekomendasi_kos1
//        p1.nama = "Kos Bunga"
//        p1.deskripsi = "Kosan khusus perempuan dan wanita termurah di Bekasi"
//        p1.rate = "4.3"
//        p1.kota = "Bekasi"
//        p1.harga = "Rp.700.000/bln"
//
//        val p2 = Rekomendasi()
//        p2.gambar = R.drawable.dummy_rekomendasi_kos2
//        p2.nama = "Kos Elite"
//        p2.deskripsi = "Kosan murah dengan sarana elite dan kualitas terjamin"
//        p2.rate = "4.4"
//        p2.kota = "Bekasi"
//        p2.harga = "Rp.1.200.000/bln"
//
//        val p3 = Rekomendasi()
//        p3.gambar = R.drawable.dummy_rekomendasi_kos3
//        p3.nama = "Kos Wkwkw"
//        p3.deskripsi = "Kosan murah dengan sarana elite dan kualitas terjamin"
//        p3.rate = "4.1"
//        p3.kota = "Bekasi"
//        p3.harga = "Rp.2.000.000/bln"
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//
//        return arr
//    }

}