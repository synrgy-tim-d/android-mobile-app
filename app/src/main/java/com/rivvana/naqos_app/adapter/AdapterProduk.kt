package com.rivvana.naqos_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.model.Produk

class AdapterProduk(var data: ArrayList<Produk>):
    RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view){
//        val imgKos = view.findViewById<ImageView>(R.id.img_gambar)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc)
        val tvKota = view.findViewById<TextView>(R.id.tv_loc)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_rekomendasi, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.imgKos.setImageResource(data[position].gambar)
        holder.tvNama.text = data[position].name
        holder.tvDesc.text = data[position].description
        holder.tvKota.text = data[position].address

    }

    override fun getItemCount(): Int {
        return data.size
    }
}