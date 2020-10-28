package com.example.dialerapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telecom.TelecomManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


const val CALL_REQUEST_CODE = 1
const val REQUEST_CODE_SET_DEFAULT_DIALER = 2

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

    override fun onStart() {
        super.onStart()
        checkDefaultDialer()
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

    fun makePhoneCall(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        if (dialIntent.resolveActivity(App.instance.packageManager) != null) {
            startActivity(dialIntent)
        } else {
            Log.e("apple", "Can't resolve app for ACTION_DIAL Intent.")
        }
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

    private fun checkDefaultDialer() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return

        /*val telecomManager = getSystemService(TELECOM_SERVICE) as TelecomManager
        val isAlreadyDefaultDialer = packageName == telecomManager.defaultDialerPackage
        if (isAlreadyDefaultDialer) return

        val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
            .putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName)
        startActivityForResult(intent, REQUEST_CODE_SET_DEFAULT_DIALER)*/

        if (getSystemService(TelecomManager::class.java).defaultDialerPackage != packageName) {
            val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
                .putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName)
            //startActivityForResult(intent, REQUEST_CODE_SET_DEFAULT_DIALER)
            startActivityForResult(intent, REQUEST_CODE_SET_DEFAULT_DIALER)
        }
    }

    private fun checkSetDefaultDialerResult(resultCode: Int) {
        val message = when (resultCode) {
            RESULT_OK       -> "User accepted request to become default dialer"
            RESULT_CANCELED -> "User declined request to become default dialer"
            else            -> "Unexpected result code $resultCode"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_SET_DEFAULT_DIALER -> checkSetDefaultDialerResult(resultCode)
        }
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_SET_DEFAULT_DIALER -> checkSetDefaultDialerResult(resultCode)
        }
    }
}