package com.example.dialerapp.view.blockedcontactlist

import androidx.lifecycle.MutableLiveData
import com.example.domain.base.BaseViewState
import com.example.domain.base.Status
import com.example.ui.base.adapter.BaseBindingRVModel

class BlockedContactListViewState(status: Status, error: Throwable? = null, data: List<BaseBindingRVModel>? = null ) : BaseViewState<List<BaseBindingRVModel>>(status, error, data) {
    val liveDataBlockedUserContactList: MutableLiveData<List<BaseBindingRVModel>> = MutableLiveData(mutableListOf())
    val liveDataBlockedContactScreen: MutableLiveData<Boolean> = MutableLiveData(false)
}