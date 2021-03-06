package com.example.dialerapp.view.dialer

import androidx.lifecycle.MutableLiveData
import com.example.domain.base.BaseViewState
import com.example.domain.base.Status

class DialerViewState(status: Status, error: Throwable? = null, data: Any? = null ) : BaseViewState<Any>(status, error, data) {

    val contactNumber: MutableLiveData<String> = MutableLiveData("")
    val liveDataMakeCall: MutableLiveData<Boolean> = MutableLiveData(false)
}