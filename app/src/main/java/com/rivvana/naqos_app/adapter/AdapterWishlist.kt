package com.rivvana.naqos_app.adapter

import android.app.Activity
import android.media.session.MediaSessionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.statuswishlist.Status
import com.rivvana.naqos_app.auth.model.wishlist.DataWishlist
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterWishlist(
    var sessionManager: SessionManager,
    val activity: Activity,
    var data: List<DataWishlist>
) : RecyclerView.Adapter<AdapterWishlist.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_name)
        val btnFav = view.findViewById<ImageView>(R.id.btn_fav)

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

        holder.btnFav.setOnClickListener {
            ApiConfig.instanceRetrofit.deleteWishlist(kostId = data[position].id,
                token = "Bearer ${sessionManager.fetchAuthToken()}"
            ).enqueue(object : Callback<Status>{
                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                    val respon = response.body()
                    val responError = response.errorBody()
                    if (respon!=null){
                        Log.d("WISHLIST DELETE", respon.toString())
                        Log.d("WISHLIST DELETE", respon.message)
                        Toast.makeText(activity, respon.toString(), Toast.LENGTH_SHORT).show()
                        this.notify()
                    }else {
                        Log.d("WISHLIST DELETE ERROR", responError.toString())
                        Toast.makeText(activity, "Error "+responError.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}