package com.rivvana.naqos_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.model.Rekomendasi

class AdapterRekomendasi(var data: ArrayList<Rekomendasi>):
    RecyclerView.Adapter<AdapterRekomendasi.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val imgKos = view.findViewById<ImageView>(R.id.img_gambar)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc)
        val tvRate = view.findViewById<TextView>(R.id.tv_rate)
        val tvKota = view.findViewById<TextView>(R.id.tv_loc)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_rekomendasi, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.imgKos.setImageResource(data[position].gambar)
        holder.tvNama.text = data[position].nama
        holder.tvDesc.text = data[position].deskripsi
        holder.tvRate.text = data[position].rate
        holder.tvKota.text = data[position].kota
        holder.tvHarga.text = data[position].harga

    }

    override fun getItemCount(): Int {
        return data.size
    }
}