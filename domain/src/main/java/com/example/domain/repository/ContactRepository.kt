package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.ContactUiModel

interface ContactRepository {
    suspend fun getContactList(isBlocked: Boolean): List<ContactUiModel>
    suspend fun getBlockedContactList(isBlocked: Boolean): List<ContactUiModel>
    suspend fun addContact(contactUiModel: ContactUiModel)
    suspend fun updateContact(contactUiModel: ContactUiModel)
    suspend fun deleteContact(contactUiModel: ContactUiModel)
    suspend fun blockContact(contactUiModel: ContactUiModel)
}