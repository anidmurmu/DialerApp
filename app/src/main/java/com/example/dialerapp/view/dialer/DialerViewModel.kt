package com.example.dialerapp.view.dialer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.base.Status
import com.example.ui.base.RxAwareViewModel
import com.example.ui.base.ViewOnClickListener

class DialerViewModel : RxAwareViewModel(), ViewOnClickListener {

    private val _mutableLiveDataDialerViewState= MutableLiveData<DialerViewState>()
    val liveDataDialerViewState: LiveData<DialerViewState> = _mutableLiveDataDialerViewState

    fun init() {
        _mutableLiveDataDialerViewState.value = DialerViewState(Status.LOADING)
    }

    override fun onViewClick(id: Int, data: Any) {

    }
}