package com.rivvana.naqos_app.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.auth.view.OtpSuccessActivity
import com.rivvana.naqos_app.databinding.ActivitySplashBinding

class SuccesActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes)

        val handler = Handler()
        handler.postDelayed({gotoOTP()},3000)
    }

    private fun gotoOTP() {
        startActivity(Intent(this@SuccesActivity, UploadActivity::class.java))
        finish()
    }
}