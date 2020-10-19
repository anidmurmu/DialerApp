package com.example.domain.usecase

import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

interface DeleteContactUseCase {
    suspend fun deleteContact(contactUiModel: ContactUiModel)
}

class DeleteContactUseCaseImpl(private val contactRepository: ContactRepository) : DeleteContactUseCase {
    override suspend fun deleteContact(contactUiModel: ContactUiModel) {
        contactRepository.deleteContact(contactUiModel)
    }
}