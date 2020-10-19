package com.example.dialerapp.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.database.ContactListDatabase
import com.example.dialerapp.App
import com.example.domain.base.Status
import com.example.domain.model.ContactUiModel
import com.example.domain.usecase.GetContactListUseCase
import com.example.ui.base.RxAwareViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListViewModel(private val getContactListUseCase: GetContactListUseCase) : RxAwareViewModel() {

  private val _contactListMutableLiveData = MutableLiveData<ContactListViewState>()
  val contactListLiveData: LiveData<ContactListViewState> = _contactListMutableLiveData

  fun init() {
    _contactListMutableLiveData.value = ContactListViewState((Status.LOADING))
  }

  init {
    /*val contactListDatabase = ContactListDatabase.getDatabase(viewModelScope, App.instance)
    val contactDao = contactListDatabase?.getContactDao()
    repository = WordRepository(wordsDao)
    allWords = repository.allWords*/
    var contactList: LiveData<List<ContactUiModel>>? = null
    viewModelScope.launch(Dispatchers.IO) {
      contactList = getContactListUseCase.getContactList()
      Log.d("apple", contactList?.value?.size.toString() + " this is size")
    }


  }

  fun getContactList() {

  }
}