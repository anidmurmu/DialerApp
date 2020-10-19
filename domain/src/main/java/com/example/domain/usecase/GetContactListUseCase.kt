package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

interface GetContactListUseCase {
  suspend fun getContactList(): LiveData<List<ContactUiModel>>
}

class GetContactListUseCaseImpl(private val repository: ContactRepository) : GetContactListUseCase {
  override suspend fun getContactList(): LiveData<List<ContactUiModel>> {
    return repository
        .getContactList()
  }
}