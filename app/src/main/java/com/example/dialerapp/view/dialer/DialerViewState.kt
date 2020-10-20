package com.example.dialerapp.view.dialer

import com.example.domain.base.BaseViewState
import com.example.domain.base.Status

class DialerViewState(status: Status, error: Throwable? = null, data: Any? = null ) : BaseViewState<Any>(status, error, data) {
}