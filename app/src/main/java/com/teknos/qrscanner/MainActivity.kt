package com.teknos.qrscanner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.teknos.qrscanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener { initScanner() }
    }

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setPrompt("Escanea un código de barras o QR")
        integrator.setTorchEnabled(false)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val tvResult: TextView = findViewById(R.id.tvResult)
        if (requestCode == IntentIntegrator.REQUEST_CODE && resultCode == RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            result?.contents?.let { contents ->
                if (isUrl(contents)) {
                    openBrowser(contents)
                } else {
                    tvResult.text = contents
                }
            } ?: Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun isUrl(text: String): Boolean {
        return Patterns.WEB_URL.matcher(text).matches()
    }

    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}