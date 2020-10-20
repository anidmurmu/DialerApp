package com.example.dialerapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


const val CALL_REQUEST_CODE = 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val version = Build.VERSION.SDK_INT
        if (version > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyHavePermission()) {
                requestForSpecificPermission()
            }
        }
    }

    private fun checkIfAlreadyHavePermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestForSpecificPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CALL_PHONE),
            CALL_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CALL_REQUEST_CODE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //granted
            } else {
                //not granted
                requestForSpecificPermission()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        }
    }
}