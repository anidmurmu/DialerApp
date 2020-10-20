package com.example.dialerapp.view.contactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dialerapp.R
import com.example.domain.base.Status
import com.example.domain.model.ContactUiModel
import com.example.domain.usecase.GetContactListUseCase
import com.example.ui.base.RxAwareViewModel
import com.example.ui.base.ViewOnClickListener
import com.example.ui.base.adapter.BaseBindingRVModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactListViewModel(private val getContactListUseCase: GetContactListUseCase) :
    RxAwareViewModel(), ViewOnClickListener {

    private val _contactListMutableLiveData = MutableLiveData<ContactListViewState>()
    val contactListLiveData: LiveData<ContactListViewState> = _contactListMutableLiveData

    fun init() {
        _contactListMutableLiveData.value =
            ContactListViewState(Status.LOADING)
    }

    init {
        getContactList()
    }

    private fun getContactList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getContactListUseCase.getContactList(isBlocked = false)
          viewModelScope.launch(Dispatchers.Main) {
              val contactList = getViewableData(list)
              /*_contactListMutableLiveData.value =
                ContactListViewState(
                  status = Status.SUCCESS,
                  data = contactList,
                  error = null
                )
              _contactListMutableLiveData.value?.data = contactList*/
                _contactListMutableLiveData.value?.liveDataUserContactList?.postValue(contactList)
                if (contactList?.size != 0) {
                    _contactListMutableLiveData.value?.liveDataNoContactTextVisibility?.postValue(
                        false
                    )
                    _contactListMutableLiveData.value?.liveDataRecyclerViewVisibility?.postValue(
                        true
                    )
                }
            }

        }
    }

    private fun getViewableData(data: List<ContactUiModel>?): List<BaseBindingRVModel>? {
        if (data.isNullOrEmpty()) return null

        val userContactList = mutableListOf<BaseBindingRVModel>()

        data.forEach {
            userContactList.add(
                ContactListRVModel(
                    it
                )
            )
        }

        return userContactList
    }

    override fun onViewClick(id: Int, data: Any) {
        when (id) {
            R.id.onclick_btn_add_contact -> {
                _contactListMutableLiveData.value?.liveDataContactScreen?.postValue(true)
            }

            R.id.onclick_btn_dialer -> {
                _contactListMutableLiveData.value?.liveDataDialerScreen?.postValue(true)
            }
        }
    }
}