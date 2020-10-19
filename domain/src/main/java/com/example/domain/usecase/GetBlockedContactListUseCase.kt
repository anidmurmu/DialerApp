package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

interface GetBlockedContactListUseCase {
    suspend fun getBlockedContactList(): LiveData<List<ContactUiModel>>
}

class GetBlockedContactListUseCaseImpl(private val contactRepository: ContactRepository) : GetBlockedContactListUseCase {
    override suspend fun getBlockedContactList(): LiveData<List<ContactUiModel>> {
        return contactRepository
            .getBlockedContactList()
    }
}