package com.rivvana.naqos_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.adapter.AdapterRekomendasi
import com.rivvana.naqos_app.model.Rekomendasi

class SearchFragment : Fragment() {

    lateinit var rvRekomendasi: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        rvRekomendasi = view.findViewById(R.id.rv_rekomendasi)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvRekomendasi.adapter = AdapterRekomendasi(arrRekomendasi)
        rvRekomendasi.layoutManager = layoutManager

        return view
    }

    val arrRekomendasi: ArrayList<Rekomendasi>
        get() {
            val arr = ArrayList<Rekomendasi>()
            val p1 = Rekomendasi()
            p1.gambar = R.drawable.dummy_rekomendasi_kos1
            p1.nama = "Nama Kos"
            p1.deskripsi = "Rincian alamat kos secara lengkap dan kode pos"
            p1.rate = "4.5"
            p1.kota = "Kota"
            p1.harga = "Rp.2.000.000/bln"

            val p2 = Rekomendasi()
            p2.gambar = R.drawable.dummy_rekomendasi_kos1
            p2.nama = "Nama Kos"
            p2.deskripsi = "Rincian alamat kos secara lengkap dan kode pos"
            p2.rate = "4.5"
            p2.kota = "Kota"
            p2.harga = "Rp.2.000.000/bln"

            val p3 = Rekomendasi()
            p3.gambar = R.drawable.dummy_rekomendasi_kos1
            p3.nama = "Nama Kos"
            p3.deskripsi = "Rincian alamat kos secara lengkap dan kode pos"
            p3.rate = "4.5"
            p3.kota = "Kota"
            p3.harga = "Rp.2.000.000/bln"

            arr.add(p1)
            arr.add(p2)
            arr.add(p3)

            return arr
        }
}