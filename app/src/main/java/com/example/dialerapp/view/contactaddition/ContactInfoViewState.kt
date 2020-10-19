package com.example.dialerapp.view.contactaddition

import androidx.lifecycle.MutableLiveData
import com.example.domain.base.BaseViewState
import com.example.domain.base.Status

class ContactInfoViewState(status: Status, error: Throwable? = null, data: Any? = null) :
    BaseViewState<Any>(status, error, data) {

    val firstName: MutableLiveData<String> = MutableLiveData("")
    val lastName: MutableLiveData<String> = MutableLiveData("")
    val contactNumber: MutableLiveData<String> = MutableLiveData("")
    val emailId: MutableLiveData<String> = MutableLiveData("")

    val firstNameError: MutableLiveData<String> = MutableLiveData("")
    val lastNameError: MutableLiveData<String> = MutableLiveData("")
    val contactNumberError: MutableLiveData<String> = MutableLiveData("")
    val emailIdError: MutableLiveData<String> = MutableLiveData("")

    val liveDataToContactList: MutableLiveData<Boolean> = MutableLiveData(false)
}