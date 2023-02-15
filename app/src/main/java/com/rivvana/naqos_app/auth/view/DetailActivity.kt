package com.rivvana.naqos_app.auth.view

import android.app.AlertDialog
import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.app.ApiConfig
import com.rivvana.naqos_app.auth.model.WishlistReq
import com.rivvana.naqos_app.auth.model.WishlistResponse
import com.rivvana.naqos_app.auth.model.statuswishlist.Status
import com.rivvana.naqos_app.auth.viewmodel.SessionManager
import com.rivvana.naqos_app.components.DialogInputFragment
import com.rivvana.naqos_app.databinding.ActivityDetailBinding
import com.rivvana.naqos_app.model.Data
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        if (sessionManager.getStatusLogin()){
            checkStatusWishlist()
        }
        buttonManager()
        getInfo()
    }

    private fun checkStatusWishlist() {
        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Data>(data, Data::class.java)
        ApiConfig.instanceRetrofit.checkStatusWishlist(
            kostId = produk.id.toString(),
            token = "Bearer ${sessionManager.fetchAuthToken()}"
        ).enqueue(object : Callback<Status>{
            override fun onResponse(
                call: Call<Status>,
                response: Response<Status>
            ) {
                val respon = response.body()
                val responError = response.errorBody()
                if (respon!=null){
                    Log.d("GET WISHLIST STATUS", respon.toString())
                    Log.d("GET WISHLIST STATUS", respon.message)
//                    Toast.makeText(this@DetailActivity, respon.toString(), Toast.LENGTH_SHORT).show()
                }else {
                    Log.d("NULL WISHLIST STATUS", responError.toString())
//                    Toast.makeText(this@DetailActivity, "Error "+responError.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                call: Call<Status>,
                t: Throwable
            ) {
                Log.d("ERROR WISHLIST STATUS", t.message.toString())
            }

        })
    }


    private fun buttonManager() {
        btnSave()
        btnSewa()
        btnFAQ()
    }

    private fun btnFAQ() {
        binding.tvFaq.setOnClickListener{
            if (binding.tvQ1.visibility == View.VISIBLE){
                binding.tvFaq.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.baseline_keyboard_arrow_down_24,0)
                binding.tvQ1.visibility = View.GONE
                binding.tvQ2.visibility = View.GONE
                binding.tvQ3.visibility = View.GONE
                binding.tvA1.visibility = View.GONE
                binding.tvA2.visibility = View.GONE
                binding.tvA3.visibility = View.GONE
            } else {
                binding.tvFaq.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.baseline_keyboard_arrow_up_24,0)
                binding.tvQ1.visibility = View.VISIBLE
                binding.tvQ2.visibility = View.VISIBLE
                binding.tvQ3.visibility = View.VISIBLE
                binding.tvA1.visibility = View.VISIBLE
                binding.tvA2.visibility = View.VISIBLE
                binding.tvA3.visibility = View.VISIBLE
            }
        }
    }


    private fun btnSave() {
        binding.btnSave.setOnClickListener{
            if (sessionManager.getStatusLogin()){
                insert()
            } else {
                showDialog()
            }
        }
    }

    private fun showDialog() {
            val dialog = layoutInflater.inflate(R.layout.dialog_login, null)
            val customDialog = AlertDialog.Builder(this)
                .setView(dialog)
                .show()

            val btnDismiss = dialog.findViewById<Button>(R.id.btn_dismiss)
            btnDismiss.setOnClickListener{
                customDialog.dismiss()
            }

            val btnLogin = dialog.findViewById<Button>(R.id.btn_login)
            btnLogin.setOnClickListener{
                startActivity(Intent(this, LoginActivity::class.java))
            }
    }

    fun insert(){

        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Data>(data, Data::class.java)
        val wishlist = WishlistReq(
            produk.id.toString()
        )

        ApiConfig.instanceRetrofit.addWishlist(wishlist,token = "Bearer ${sessionManager.fetchAuthToken()}"
        ).enqueue(object : Callback<WishlistResponse>{
            override fun onResponse(
                call: Call<WishlistResponse>,
                response: Response<WishlistResponse>
            ) {
                val respon = response.body()
                val responError = response.errorBody()
                if (respon!=null){
                    Log.d("GET WISHLIST", respon.toString())
                    Toast.makeText(this@DetailActivity, "${response.body().toString()}", Toast.LENGTH_SHORT).show()
                }else {
                    Log.d("ERROR WISHLIST", responError.toString())
                    Toast.makeText(this@DetailActivity, "Error "+responError.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WishlistResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun btnSewa() {
        binding.btnSewa.setOnClickListener{
            val showInputDate = DialogInputFragment()
            showInputDate.show((this as AppCompatActivity).supportFragmentManager, "showInputDate")
        }
    }

    private fun getInfo() {
        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Data>(data, Data::class.java)
        Log.d("RESPON PRODUK", produk.toString())

        val imgKos = findViewById<ImageView>(R.id.img_kos_detail)
        val imgPKos = findViewById<ImageView>(R.id.img_profile)
        binding.tvNama.text = produk.name
        binding.tvAddress.text = produk.address
        binding.tvRate.text = produk.kostRating.toString()
        binding.tvLoc.text = produk.city?.city
        binding.tvNamaPemilik.text = produk.ownerId?.fullname
        binding.tvNamaKos.text = "Pemilik "+produk.name
        binding.btnWa.text = produk.ownerId?.phoneNumber
        binding.tvQ1.text = produk.question1
        binding.tvA1.text = produk.answer1
        binding.tvQ2.text = produk.question2
        binding.tvA2.text = produk.answer2
        binding.tvQ3.text = produk.question3
        binding.tvA3.text = produk.answer3
//        binding.tvHarga.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(produk.rooms[0].pricePerMonthly!!.toInt()))

        //set img kos
        val img = produk.imageKosts[0].url
        Picasso.get()
            .load(img)
            .placeholder(R.drawable.dummy_rekomendasi_kos1)
            .error(R.drawable.dummy_rekomendasi_kos1)
            .into(imgKos)

        //set image profile
        val imgProfile = produk.ownerId?.imgUrl
        Picasso.get()
            .load(imgProfile)
            .placeholder(R.drawable.dummy_photo_profil)
            .error(R.drawable.dummy_photo_profil)
            .into(imgPKos)
    }

}