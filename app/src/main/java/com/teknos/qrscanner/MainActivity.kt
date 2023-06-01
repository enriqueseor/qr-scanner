package com.teknos.qrscanner

import android.content.Intent
import android.os.Bundle
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
        IntentIntegrator(this).initiateScan()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val tvResult: TextView = findViewById(R.id.tvResult)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents == null) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
            }else{
                tvResult.text = result.contents
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}