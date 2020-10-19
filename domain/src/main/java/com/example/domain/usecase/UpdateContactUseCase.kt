package com.example.domain.usecase

import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

interface UpdateContactUseCase {
    suspend fun updateContact(contactUiModel: ContactUiModel)
}

class UpdateContactUseCaseImpl(private val contactRepository: ContactRepository) : UpdateContactUseCase {
    override suspend fun updateContact(contactUiModel: ContactUiModel) {
        contactRepository.updateContact(contactUiModel)
    }
}