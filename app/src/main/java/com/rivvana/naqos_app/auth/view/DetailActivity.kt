package com.rivvana.naqos_app.auth.view

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.rivvana.naqos_app.databinding.ActivityDetailBinding
import com.rivvana.naqos_app.model.Data
import com.rivvana.naqos_app.model.WishlistModel
import com.rivvana.naqos_app.room.MyDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnSave()
        btnSewa()
        getInfo()
    }

    private fun btnSave() {
        binding.btnSave.setOnClickListener{
            insert()
        }
    }

    fun insert(){
        val myDb: MyDatabase = MyDatabase.getInstance(this)!! // call database
        val wishlist = WishlistModel() //create new note
        wishlist.name = "First Note"

        CompositeDisposable().add(Observable.fromCallable { myDb.daoWishlist().insert(wishlist) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("RESPON ADD", "DATA MASUK")
            })
    }

    private fun btnSewa() {
        binding.btnSewa.setOnClickListener{

        }
    }

    private fun getInfo() {
        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Data>(data, Data::class.java)
        Log.d("RESPON PRODUK", produk.toString())
//        binding.imgKosDetail
        binding.tvNama.text = produk.name
        binding.tvAddress.text = produk.address
//        binding.tvRate.text = produk.rate
        binding.tvLoc.text = produk.city?.city
//        binding.imgProfile
        binding.tvNamaPemilik.text = produk.ownerId?.fullname
        binding.tvNamaKos.text = "Pemilik "+produk.name
        binding.btnWa.text = produk.ownerId?.phoneNumber

        //set img
        //val img = "https://be-naqos.up.railway.app/api/"+data[position].image
//        Picasso.get()
//            .load(img)
//            .placeholder(R.drawable.dummy_rekomendasi_kos1)
//            .error(R.drawable.dummy_rekomendasi_kos1)
//            .into(imgKos)
    }
}