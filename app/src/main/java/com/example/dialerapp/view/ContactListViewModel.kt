package com.example.dialerapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.base.Status
import com.example.ui.base.RxAwareViewModel

class ContactListViewModel : RxAwareViewModel() {

    private val _contactListMutableLiveData = MutableLiveData<ContactListViewState>()
    val contactListLiveData: LiveData<ContactListViewState> = _contactListMutableLiveData

    fun init() {
        _contactListMutableLiveData.value = ContactListViewState((Status.LOADING))
    }


}