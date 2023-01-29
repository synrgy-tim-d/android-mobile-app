package com.rivvana.naqos_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.model.Produk
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterProduk(var data: ArrayList<Produk>):
    RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val imgKos = view.findViewById<ImageView>(R.id.img_gambar)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc)
        val tvRate = view.findViewById<TextView>(R.id.tv_rate)
        val tvKota = view.findViewById<TextView>(R.id.tv_loc)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //        holder.imgKos.setImageResource(dataposition].gambar)[
        holder.tvNama.text = data[position].name
//        holder.tvHarga.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(data[position].harga))
        holder.tvDesc.text = data[position].description
        holder.tvKota.text = data[position].address
//        val image = "https://be-naqos.up.railway.app/api/"+data[position].image
//        Picasso.get()
//            .load(image)
//            .placeholder(R.drawable.dummy_rekomendasi_kos1)
//            .error(R.drawable.dummy_rekomendasi_kos1)
//            .into(holder.imgKos)


    }

    override fun getItemCount(): Int {
        return data.size
    }
}