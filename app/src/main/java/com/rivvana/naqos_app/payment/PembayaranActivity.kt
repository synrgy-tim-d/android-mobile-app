package com.rivvana.naqos_app.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.databinding.ActivityPembayaranBinding

class PembayaranActivity : AppCompatActivity() {
    lateinit var binding: ActivityPembayaranBinding
    val arrSpinner = arrayOf("Transfer Bank", "Tunai")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        initSpinner()
        btnBayar()
        setContentView(binding.root)
    }

    private fun btnBayar() {
        binding.btnBayar.setOnClickListener {
            val intent = Intent(this, SuccesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initSpinner() {
        val arrayAdapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrSpinner)
        binding.spinnerPayment.adapter = arrayAdapter
        binding.spinnerPayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, parent: View?, position: Int, Id: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}