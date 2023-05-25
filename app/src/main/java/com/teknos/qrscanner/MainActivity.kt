package com.teknos.qrscanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.teknos.qrscanner.singleton.Singleton

class MainActivity : AppCompatActivity() {

    private var singleton: Singleton? = Singleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResult: TextView = findViewById(R.id.tvResult)
        tvResult.text = singleton?.result

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