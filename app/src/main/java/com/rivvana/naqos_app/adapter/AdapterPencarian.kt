package com.rivvana.naqos_app.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.auth.model.pencarian.Data

class AdapterPencarian(
    var data: List<Data>,
    var activity: Activity
) : RecyclerView.Adapter<AdapterPencarian.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("Not yet implemented")
    }

}