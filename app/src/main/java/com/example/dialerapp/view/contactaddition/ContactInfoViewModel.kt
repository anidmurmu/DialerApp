package com.example.dialerapp.view.contactaddition

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dialerapp.App
import com.example.dialerapp.R
import com.example.domain.base.Status
import com.example.ui.base.RxAwareViewModel
import com.example.ui.base.ViewOnClickListener
import com.example.ui.helper.isContactNumberValid
import com.example.ui.helper.isEmailValid
import com.example.ui.helper.isNameValid

class ContactInfoViewModel : RxAwareViewModel(), ViewOnClickListener {

    private val _mutableLiveDataContactInfoViewState = MutableLiveData<ContactInfoViewState>()
    val liveDataContactInfoViewState: LiveData<ContactInfoViewState> =
        _mutableLiveDataContactInfoViewState

    fun init() {
        _mutableLiveDataContactInfoViewState.value =
            ContactInfoViewState(Status.LOADING)
    }

    private fun validateEmail(email: String?): Boolean {
        if (email.isNullOrEmpty()) {
            _mutableLiveDataContactInfoViewState.value?.emailIdError?.postValue(
                App.instance.getString(
                    R.string.email_empty
                )
            )
            return false
        }

        if (!isEmailValid(email)) {
            _mutableLiveDataContactInfoViewState.value?.emailIdError?.postValue(
                App.instance.getString(
                    R.string.email_error
                )
            )
            return false
        }

        _mutableLiveDataContactInfoViewState.value?.emailIdError?.postValue("")
        return true
    }

    private fun validateName(name: String?): Boolean {
        if (name.isNullOrEmpty()) {
            _mutableLiveDataContactInfoViewState.value?.firstNameError?.postValue(
                App.instance.getString(
                    R.string.name_empty
                )
            )
            return false
        }

        if (!isNameValid(name)) {
            _mutableLiveDataContactInfoViewState.value?.firstNameError?.postValue(
                App.instance.getString(
                    R.string.name_error
                )
            )
            return false
        }

        _mutableLiveDataContactInfoViewState.value?.firstNameError?.postValue("")
        return true
    }

    private fun validateContactNumber(contactNumber: String?): Boolean {
        if (contactNumber.isNullOrEmpty()) {
            _mutableLiveDataContactInfoViewState.value?.contactNumberError?.postValue(
                App.instance.getString(
                    R.string.contact_info_empty
                )
            )
            return false
        }

        if (!isContactNumberValid(contactNumber)) {
            _mutableLiveDataContactInfoViewState.value?.contactNumberError?.postValue(
                App.instance.getString(
                    R.string.contact_info_error
                )
            )
            return false
        }

        _mutableLiveDataContactInfoViewState.value?.contactNumberError?.postValue("")
        return true
    }

    private fun isFormValid(): Boolean {
        return validateName(liveDataContactInfoViewState.value?.firstName?.value) &&
                validateName(liveDataContactInfoViewState.value?.lastName?.value) &&
                validateContactNumber(liveDataContactInfoViewState.value?.contactNumber?.value) &&
                validateEmail(liveDataContactInfoViewState.value?.emailId?.value)
    }

    override fun onViewClick(id: Int, data: Any) {
        when (id) {
            R.id.onclick_btn_save_contact -> {
                if (isFormValid()) {
                    Toast.makeText(App.instance, "working", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}