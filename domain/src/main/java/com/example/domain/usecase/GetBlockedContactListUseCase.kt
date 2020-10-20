package com.example.domain.usecase

import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

interface GetBlockedContactListUseCase {
    suspend fun getBlockedContactList(isBlocked: Boolean): List<ContactUiModel>
}

class GetBlockedContactListUseCaseImpl(private val contactRepository: ContactRepository) : GetBlockedContactListUseCase {
    override suspend fun getBlockedContactList(isBlocked: Boolean): List<ContactUiModel> {
        return contactRepository
            .getBlockedContactList(isBlocked)
    }
}