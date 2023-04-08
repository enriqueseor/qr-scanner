package com.teknos.qrcodescanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanActivity()
    }

    private fun scanActivity() {
        val btn = findViewById<Button>(R.id.btnScan)
        btn.setOnClickListener {
            val myIntent = Intent(this@MainActivity, ScannerActivity::class.java)
            this@MainActivity.startActivity(myIntent)
        }
    }
}