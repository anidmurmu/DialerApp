package com.example.domain.usecase

import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

interface AddContactUseCase {
    suspend fun addContact(contactUiModel: ContactUiModel)
}

class AddContactUseCaseImpl(private val contactRepository: ContactRepository) : AddContactUseCase {
    override suspend fun addContact(contactUiModel: ContactUiModel) {
        contactRepository.addContact(contactUiModel)
    }
}