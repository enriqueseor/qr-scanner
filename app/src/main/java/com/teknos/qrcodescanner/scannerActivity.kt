package com.teknos.qrcodescanner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val PERMISSION_REQUEST_CAMERA: Int = 1
    private var mScannerView: ZXingScannerView? = null
    private var cameraOn = false
    private var contentFrame: ConstraintLayout? = null
    private var singleton: Singleton? = Singleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        contentFrame = findViewById(R.id.contentFrame)
        mScannerView = ZXingScannerView(this)
        cameraOn = false
        Log.i("AD_C11", "onCreate")
        captureQR()
    }

    private fun startCamera() {
        Log.i("AD_C11", "startCamera")
        contentFrame!!.addView(mScannerView)
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()
        cameraOn = true
    }

    private fun stopCamera() {
        Log.i("AD_C11", "stopCamera")
        mScannerView?.stopCamera()
        contentFrame!!.removeView(mScannerView)
        cameraOn = false
    }

    override fun handleResult(rawResult: Result?) {
        Log.i("AD_C11", "handleResult")
        if (rawResult != null) {
            singleton?.result = rawResult.text
            val openURL = Intent(Intent.ACTION_VIEW, Uri.parse(rawResult.text))
            startActivity(openURL)
        }
        stopCamera()
        finish()
    }

    private fun captureQR() {
        Log.i("AD_C11", "captureQR")
        if (Build.VERSION.SDK_INT >= 25) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("AD_C11", "onPause")
        if (cameraOn) stopCamera()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("AD_C11", "onRestart")
        if (!cameraOn) startCamera()
    }
}