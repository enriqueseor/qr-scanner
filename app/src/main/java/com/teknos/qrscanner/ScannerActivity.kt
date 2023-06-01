package com.teknos.qrscanner

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ScannerActivity : AppCompatActivity(){

    private val permissionRequestCamera: Int = 1
    private var cameraOn = false
    private var contentFrame: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        contentFrame = findViewById(R.id.contentFrame)
        Log.i("AD_C11", "onCreate")
    }
}