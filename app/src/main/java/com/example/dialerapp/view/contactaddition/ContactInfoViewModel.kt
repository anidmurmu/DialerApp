package com.example.dialerapp.view.contactaddition

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dialerapp.App
import com.example.dialerapp.R
import com.example.domain.base.Status
import com.example.domain.model.ContactUiModel
import com.example.domain.usecase.AddContactUseCase
import com.example.domain.usecase.GetContactListUseCase
import com.example.ui.base.RxAwareViewModel
import com.example.ui.base.ViewOnClickListener
import com.example.ui.helper.isContactNumberValid
import com.example.ui.helper.isEmailValid
import com.example.ui.helper.isNameValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactInfoViewModel(private val addContactUseCase: AddContactUseCase,
                           private val getContactListUseCase: GetContactListUseCase
) : RxAwareViewModel(), ViewOnClickListener {

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

    private fun validateFirstName(name: String?): Boolean {
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

    private fun validateLastName(name: String?): Boolean {

        if (name.isNullOrEmpty()) {
            return true
        }

        if (!isNameValid(name)) {
            _mutableLiveDataContactInfoViewState.value?.lastNameError?.postValue(
                App.instance.getString(
                    R.string.name_error
                )
            )
            return false
        }

        _mutableLiveDataContactInfoViewState.value?.lastNameError?.postValue("")
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
        return validateFirstName(liveDataContactInfoViewState.value?.firstName?.value) &&
                validateLastName(liveDataContactInfoViewState.value?.lastName?.value) &&
                validateContactNumber(liveDataContactInfoViewState.value?.contactNumber?.value) &&
                validateEmail(liveDataContactInfoViewState.value?.emailId?.value)
    }

    private fun getFullName(firstName: String?, lastName: String?): String {
        if (lastName?.trim()?.isEmpty() == true) {
            return firstName?.trim() ?: ""
        }
        return firstName?.trim() + " " + lastName?.trim() ?: ""
    }

    private fun addContact() {
        viewModelScope.launch(Dispatchers.IO) {
            addContactUseCase.addContact(ContactUiModel(
                liveDataContactInfoViewState.value?.contactNumber?.value ?: "",
                getFullName(liveDataContactInfoViewState.value?.firstName?.value, liveDataContactInfoViewState.value?.lastName?.value),
                liveDataContactInfoViewState.value?.emailId?.value ?: "",
                false

            ))
        }
    }

    override fun onViewClick(id: Int, data: Any) {
        when (id) {
            R.id.onclick_btn_save_contact -> {
                if (isFormValid()) {
                    _mutableLiveDataContactInfoViewState.value?.liveDataToContactList?.postValue(true)
                    addContact()
                }
            }
        }
    }
}