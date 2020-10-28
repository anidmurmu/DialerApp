package com.example.dialerapp.view.call

import android.os.Build
import android.os.Bundle
import android.telecom.Call
import android.telecom.Call.Details
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.dialerapp.R

class CallActivity : AppCompatActivity() {

  companion object {
    private const val LOG_TAG = "CallActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_call)
  }

  override fun onResume() {
    super.onResume()
  }

  /**
   * Callback class
   * Listens to the call and do stuff when something changes
   */
  @RequiresApi(Build.VERSION_CODES.M)
  class Callback : Call.Callback() {
    override fun onStateChanged(call: Call, state: Int) {
      /*
              Call states:

              1   = Call.STATE_DIALING
              2   = Call.STATE_RINGING
              3   = Call.STATE_HOLDING
              4   = Call.STATE_ACTIVE
              7   = Call.STATE_DISCONNECTED
              8   = Call.STATE_SELECT_PHONE_ACCOUNT
              9   = Call.STATE_CONNECTING
              10  = Call.STATE_DISCONNECTING
              11  = Call.STATE_PULLING_CALL
             */
      super.onStateChanged(call, state)
    }

    override fun onDetailsChanged(
      call: Call,
      details: Details
    ) {
      super.onDetailsChanged(call, details)
    }
  }

}
