package com.example.dialerapp.view.call

import com.example.domain.base.BaseViewState
import com.example.domain.base.Status

class CallViewState(status: Status, error: Throwable? = null, data: Any? = null) :
    BaseViewState<Any>(status, error, data) {
}