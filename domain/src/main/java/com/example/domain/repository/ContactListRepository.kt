package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.ContactUiModel

interface ContactListRepository {
    suspend fun getContactList(): LiveData<List<ContactUiModel>>
}