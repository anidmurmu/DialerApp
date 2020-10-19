package com.example.domain.usecase

import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

interface BlockContactUseCase {
    suspend fun blockContact(contactUiModel: ContactUiModel)
}

class BlockContactUseCaseImpl(private val contactRepository: ContactRepository) : BlockContactUseCase {
    override suspend fun blockContact(contactUiModel: ContactUiModel) {
        contactRepository.blockContact(contactUiModel)
    }
}