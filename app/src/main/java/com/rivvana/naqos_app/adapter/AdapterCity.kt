package com.rivvana.naqos_app.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.model.DataX

class AdapterCity(
    var activity: Activity,
    var data: List<DataX>
) : RecyclerView.Adapter<AdapterCity.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val tvCity = view.findViewById<TextView>(R.id.tv_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return AdapterCity.Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvCity.text = data[position].city
    }
}