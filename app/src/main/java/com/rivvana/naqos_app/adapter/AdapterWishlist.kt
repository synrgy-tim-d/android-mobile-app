package com.rivvana.naqos_app.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.model.wishlist.Data
import com.rivvana.naqos_app.auth.model.wishlist.WishlistRespons
import com.rivvana.naqos_app.fragment.WishlistFragment

class AdapterWishlist(
    val context: Context,
    var data: List<Data>
) : RecyclerView.Adapter<AdapterWishlist.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = data[position].name
    }
}