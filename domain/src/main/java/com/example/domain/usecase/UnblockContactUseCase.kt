package com.example.domain.usecase

import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

interface UnblockContactUseCase {
    suspend fun unblockContact(contactUiModel: ContactUiModel)
}

class UnblockContactUseCaseImpl(private val contactRepository: ContactRepository) : UnblockContactUseCase {
    override suspend fun unblockContact(contactUiModel: ContactUiModel) {

    }
}