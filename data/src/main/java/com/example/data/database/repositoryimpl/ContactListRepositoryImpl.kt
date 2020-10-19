package com.example.data.database.repositoryimpl

import androidx.lifecycle.LiveData
import com.example.data.database.dao.ContactDao
import com.example.data.database.mapper.ContactEntityToContactUiMapper
import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactListRepository

class ContactListRepositoryImpl(private val dataSource: ContactDao,
                                private val contactEntityToContactUiMapper: ContactEntityToContactUiMapper) : ContactListRepository {
  override suspend fun getContactList(): LiveData<List<ContactUiModel>> {
    return contactEntityToContactUiMapper
        .mapFrom(dataSource
            .getContactList())
  }
}