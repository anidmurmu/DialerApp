package com.example.dialerapp.view.call

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.telecom.Call
import android.telecom.InCallService
import android.util.Log

@TargetApi(Build.VERSION_CODES.M)
class CallService : InCallService() {

  companion object {
    private const val LOG_TAG = "CallService"
  }

  /**
   * When call has been added
   *
   * @param call
   */
  override fun onCallAdded(call: Call) {
    super.onCallAdded(call)
    val intent = Intent(this, CallActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
    CallManager.sCall = call
  }

  /**
   * When call has been removed
   *
   * @param call
   */
  override fun onCallRemoved(call: Call?) {
    super.onCallRemoved(call)
    CallManager.sCall = null
  }

}