package com.rivvana.naqos_app.auth.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.webkit.WebViewClient
import com.rivvana.naqos_app.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private var uri: Uri? = null
    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        uri = intent.data
        val parameters = uri?.pathSegments
        val param = parameters?.get(parameters.size - 1)
        binding.webView.webViewClient = WebViewClient()

        binding.webView.loadUrl("https://be-naqos.up.railway.app/api/auth/register-confirm-otp/"+param)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)

        val handler = Handler()
        handler.postDelayed({gotoOTP()},3000)
    }

    private fun gotoOTP() {
        startActivity(Intent(this@WebViewActivity, OtpSuccessActivity::class.java))
        finish()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        else
            super.onBackPressed()
    }

}