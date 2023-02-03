package com.rivvana.naqos_app.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.view.DetailActivity
import com.rivvana.naqos_app.model.Data
import com.rivvana.naqos_app.model.ProdukKos
import java.util.*
import kotlin.collections.ArrayList

class AdapterProduk(
    var activity: Activity,
    var data: List<Data>
    ): RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val imgKos = view.findViewById<ImageView>(R.id.img_gambar)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc)
        val tvRate = view.findViewById<TextView>(R.id.tv_rate)
        val tvKota = view.findViewById<TextView>(R.id.tv_loc)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)
        val layoutProduk = view.findViewById<CardView>(R.id.layout_produk)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //        holder.imgKos.setImageResource(dataposition].gambar)
        holder.tvNama.text = data[position].name
        holder.tvDesc.text = data[position].description
//        holder.tvRate.text = data[position].rate
        holder.tvKota.text = data[position].city?.city
//        holder.tvHarga.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(data[position].harga))

//        val img = "https://be-naqos.up.railway.app/api/"+data[position].image
//        Picasso.get()
//            .load(img)
//            .placeholder(R.drawable.dummy_rekomendasi_kos1)
//            .error(R.drawable.dummy_rekomendasi_kos1)
//            .into(holder.imgKos)

        holder.layoutProduk.setOnClickListener{
            val intent = Intent(activity, DetailActivity::class.java)
            val str = Gson().toJson(data[position], Data::class.java)
            intent.putExtra("extra", str)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}