package com.example.dialerapp.view.blockedcontactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dialerapp.R
import com.example.domain.base.Status
import com.example.domain.model.ContactUiModel
import com.example.domain.usecase.DeleteContactUseCase
import com.example.domain.usecase.GetBlockedContactListUseCase
import com.example.domain.usecase.UnblockContactUseCase
import com.example.ui.base.RxAwareViewModel
import com.example.ui.base.ViewOnClickListener
import com.example.ui.base.adapter.BaseBindingRVModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BlockedContactListViewModel(
    private val getBlockedContactListUseCase: GetBlockedContactListUseCase,
    private val unblockContactUseCase: UnblockContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : RxAwareViewModel(), ViewOnClickListener {

    private val _blockedContactListViewStateMutableLiveData = MutableLiveData<BlockedContactListViewState>()
    val blockedContactListViewStateLiveData: LiveData<BlockedContactListViewState> =
        _blockedContactListViewStateMutableLiveData

    fun init() {
        _blockedContactListViewStateMutableLiveData.value =
            BlockedContactListViewState(Status.LOADING)
    }

    fun getBlockedContactList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getBlockedContactListUseCase.getBlockedContactList(isBlocked = true)
            viewModelScope.launch(Dispatchers.Main) {
                val contactList = getViewableData(list)
                _blockedContactListViewStateMutableLiveData.value?.liveDataBlockedUserContactList?.postValue(contactList)
            }

        }
    }

    private fun getViewableData(data: List<ContactUiModel>?): List<BaseBindingRVModel>? {
        if (data.isNullOrEmpty()) return null

        val userContactList = mutableListOf<BaseBindingRVModel>()

        data.forEach {
            userContactList.add(
                BlockedContactListRVModel(
                    it
                )
            )
        }

        return userContactList
    }

    private fun deleteContact(contactUiModel: ContactUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteContactUseCase.deleteContact(contactUiModel)
            getBlockedContactList()
        }
    }

    private fun unblockContact(contactUiModel: ContactUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactUiModel.isBlocked = false
            unblockContactUseCase.unblockContact(contactUiModel)
            getBlockedContactList()
        }
    }

    override fun onViewClick(id: Int, data: Any) {
        when (id) {
            R.id.onclick_btn_delete -> {
                val contactUiModel = data as ContactUiModel
                deleteContact(contactUiModel)
            }

            R.id.onclick_btn_unblock -> {
                val contactUiModel = data as ContactUiModel
                unblockContact(contactUiModel)
            }
        }
    }
}