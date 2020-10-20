package com.example.dialerapp.view.dialer

import android.R.attr.phoneNumber
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dialerapp.App
import com.example.dialerapp.R
import com.example.domain.base.Status
import com.example.ui.base.RxAwareViewModel
import com.example.ui.base.ViewOnClickListener
import com.example.ui.helper.isNumberValid


class DialerViewModel : RxAwareViewModel(), ViewOnClickListener {

    private val _mutableLiveDataDialerViewState = MutableLiveData<DialerViewState>()
    val liveDataDialerViewState: LiveData<DialerViewState> = _mutableLiveDataDialerViewState

    fun init() {
        _mutableLiveDataDialerViewState.value = DialerViewState(Status.LOADING)
    }

    fun makeCall() {

    }

    override fun onViewClick(id: Int, data: Any) {
        when (id) {
            R.id.onclick_btn_add_number -> {
                val digit = data as Int
                val previousNum = _mutableLiveDataDialerViewState.value?.contactNumber?.value
                val newNumber = previousNum + digit.toString()
                _mutableLiveDataDialerViewState.value?.contactNumber?.postValue(newNumber)
            }

            R.id.onclick_btn_clear_number -> {
                val previousNum = _mutableLiveDataDialerViewState.value?.contactNumber?.value
                val newNumber = previousNum?.dropLast(1)
                _mutableLiveDataDialerViewState.value?.contactNumber?.postValue(newNumber)
            }

            R.id.onclick_btn_char -> {
                val char = data as Char
                val previousNum = _mutableLiveDataDialerViewState.value?.contactNumber?.value
                val newNumber = previousNum + char.toString()
                _mutableLiveDataDialerViewState.value?.contactNumber?.postValue(newNumber)
            }

            R.id.onclick_btn_call -> {
                val numberToCall = _mutableLiveDataDialerViewState.value?.contactNumber?.value
                if (isNumberValid(numberToCall)) {
                    _mutableLiveDataDialerViewState.value?.liveDataMakeCall?.postValue(true)
                }
            }
        }
    }
}