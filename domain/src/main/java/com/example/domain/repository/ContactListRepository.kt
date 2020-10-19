package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.ContactUiModel

interface ContactListRepository {
    suspend fun getContactList(): LiveData<List<ContactUiModel>>
    suspend fun getBlockedContactList(): LiveData<List<ContactUiModel>>
    suspend fun addContact(contactUiModel: ContactUiModel)
    suspend fun updateContact(contactUiModel: ContactUiModel)
    suspend fun deleteContact(contactUiModel: ContactUiModel)
    suspend fun blockContact(contactUiModel: ContactUiModel)
}