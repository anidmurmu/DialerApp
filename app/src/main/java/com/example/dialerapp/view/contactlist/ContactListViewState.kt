package com.example.dialerapp.view.contactlist

import androidx.lifecycle.MutableLiveData
import com.example.domain.base.BaseViewState
import com.example.domain.base.Status
import com.example.ui.base.adapter.BaseBindingRVModel

class ContactListViewState(status: Status, error: Throwable? = null, data: List<BaseBindingRVModel>? = null ) : BaseViewState<List<BaseBindingRVModel>>(status, error, data) {

    fun getContactList() = data ?: mutableListOf()
    val liveDataContactScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val liveDataNoContactTextVisibility: MutableLiveData<Boolean> = MutableLiveData(true)
    val liveDataRecyclerViewVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val liveDataUserContactList: MutableLiveData<List<BaseBindingRVModel>> = MutableLiveData(mutableListOf())
    val liveDataDialerScreen: MutableLiveData<Boolean> = MutableLiveData(false)
}