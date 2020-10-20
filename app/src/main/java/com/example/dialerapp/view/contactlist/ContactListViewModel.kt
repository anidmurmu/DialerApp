package com.example.dialerapp.view.contactlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dialerapp.R
import com.example.domain.base.Status
import com.example.domain.model.ContactUiModel
import com.example.domain.usecase.GetContactListUseCase
import com.example.ui.base.RxAwareViewModel
import com.example.ui.base.ViewOnClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListViewModel(private val getContactListUseCase: GetContactListUseCase) : RxAwareViewModel(), ViewOnClickListener {

  private val _contactListMutableLiveData = MutableLiveData<ContactListViewState>()
  val contactListLiveData: LiveData<ContactListViewState> = _contactListMutableLiveData

  private lateinit var data : List<ContactUiModel>

  fun init() {
    _contactListMutableLiveData.value =
      ContactListViewState((Status.LOADING))
  }

  init {
    /*val contactListDatabase = ContactListDatabase.getDatabase(viewModelScope, App.instance)
    val contactDao = contactListDatabase?.getContactDao()
    repository = WordRepository(wordsDao)
    allWords = repository.allWords*/
    var contactList: LiveData<List<ContactUiModel>>? = null
    viewModelScope.launch(Dispatchers.IO) {
      //contactList = getContactListUseCase.getContactList()
      data = getContactListUseCase.getContactList()
      //data.postValue()
      //Log.d("apple", data.toString() + " this is size")
      //Log.d("apple value", data.size.toString() + " this is size")
    }


  }

  /*suspend fun getContactList(): List<BaseBindingRVModel> {
    val data = emptyList<BaseBindingRVModel>()
    val dataFromNetwork = getContactListUseCase.getContactList()
  }*/

  fun getContactList() {
    viewModelScope.launch(Dispatchers.IO) {
      val list = getContactListUseCase.getContactList()
      Log.d("apple", "$list")
      Log.d("apple value", list.size.toString() + " this is size")
    }
  }

  override fun onViewClick(id: Int, data: Any) {
    when(id) {
      R.id.onclick_btn_add_contact -> {
        _contactListMutableLiveData.value?.liveDataContactScreen?.postValue(true)
      }
    }
  }
}