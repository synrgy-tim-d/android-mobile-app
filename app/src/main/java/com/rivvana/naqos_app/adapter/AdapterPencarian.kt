package com.rivvana.naqos_app.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.model.Data
import com.rivvana.naqos_app.auth.model.pencarian.DataP
import com.rivvana.naqos_app.auth.view.DetailActivity
import com.squareup.picasso.Picasso

class AdapterPencarian(
    var activity : Activity,
    var data: List<com.rivvana.naqos_app.model.Data>,
) : RecyclerView.Adapter<AdapterPencarian.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val imgKos = view.findViewById<ImageView>(R.id.img_kos)
        val tvNama = view.findViewById<TextView>(R.id.tv_name)
        val tvRate = view.findViewById<TextView>(R.id.tv_rate)
        val tvKota = view.findViewById<TextView>(R.id.tv_loc)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)
        val layoutProduk = view.findViewById<LinearLayout>(R.id.layout_cari)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_pencarian, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = data[position].name
        holder.tvRate.text = data[position].kostRating.toString()
        holder.tvKota.text = data[position].city?.city

        val img = data[position].imageKosts[0].url
        Log.d("ISI IMG", img.toString())
        Picasso.get()
            .load(img)
            .placeholder(R.drawable.dummy_rekomendasi_kos1)
            .error(R.drawable.dummy_rekomendasi_kos1)
            .into(holder.imgKos)

        holder.layoutProduk.setOnClickListener{
            val intent = Intent(activity, DetailActivity::class.java)
            val str = Gson().toJson(data[position], com.rivvana.naqos_app.model.Data::class.java)
            intent.putExtra("extra", str)
            activity.startActivity(intent)
        }
    }

}