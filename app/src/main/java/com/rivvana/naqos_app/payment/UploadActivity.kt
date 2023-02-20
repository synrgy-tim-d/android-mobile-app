package com.rivvana.naqos_app.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rivvana.naqos_app.R

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        val handler = Handler()
        handler.postDelayed({gotoOTP()},3000)
    }

    private fun gotoOTP() {
        startActivity(Intent(this@UploadActivity, RiwayatActivity::class.java))
        finish()
    }
}