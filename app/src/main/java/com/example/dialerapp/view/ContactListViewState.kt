package com.example.dialerapp.view

import com.example.domain.base.BaseViewState
import com.example.domain.base.Status
import com.example.ui.base.adapter.BaseBindingRVModel

class ContactListViewState(status: Status, error: Throwable? = null, data: List<BaseBindingRVModel>? = null ) : BaseViewState<List<BaseBindingRVModel>>(status, error, data) {

    fun getContactList() = data ?: mutableListOf()
}