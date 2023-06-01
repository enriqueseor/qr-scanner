package com.teknos.qrscanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.teknos.qrscanner.databinding.ActivityMainBinding
import com.teknos.qrscanner.singleton.Singleton

class MainActivity : AppCompatActivity() {

    private var singleton: Singleton? = Singleton
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener { initScanner() }

        val tvResult: TextView = findViewById(R.id.tvResult)
        tvResult.text = singleton?.result

        scanActivity()
    }

    private fun initScanner() {
    }

    private fun scanActivity() {
        val btn = findViewById<Button>(R.id.btnScan)
        btn.setOnClickListener {
            val myIntent = Intent(this@MainActivity, ScannerActivity::class.java)
            this@MainActivity.startActivity(myIntent)
        }
    }
}