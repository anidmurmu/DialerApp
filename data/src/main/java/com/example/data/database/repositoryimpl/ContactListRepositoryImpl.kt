package com.example.data.database.repositoryimpl

import androidx.lifecycle.LiveData
import com.example.data.database.dao.ContactDao
import com.example.data.database.mapper.ContactEntityListToContactUiModelListMapper
import com.example.data.database.mapper.ContactUiModelToContactEntityMapper
import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactListRepository

class ContactListRepositoryImpl(
    private val dataSource: ContactDao,
    private val contactEntityListToContactUiModelListMapper: ContactEntityListToContactUiModelListMapper,
    private val contactUiModelToContactEntityMapper: ContactUiModelToContactEntityMapper
) : ContactListRepository {
    override suspend fun getContactList(): LiveData<List<ContactUiModel>> {
        return contactEntityListToContactUiModelListMapper
            .mapFrom(
                dataSource
                    .getContactList()
            )
    }

    override suspend fun getBlockedContactList(): LiveData<List<ContactUiModel>> {
        return contactEntityListToContactUiModelListMapper
            .mapFrom(
                dataSource
                    .getBlockedContactList()
            )
    }

    override suspend fun addContact(contactUiModel: ContactUiModel) {
        dataSource.addContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }

    override suspend fun updateContact(contactUiModel: ContactUiModel) {
        dataSource.updateContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }

    override suspend fun deleteContact(contactUiModel: ContactUiModel) {
        dataSource.deleteContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }

    override suspend fun blockContact(contactUiModel: ContactUiModel) {
        dataSource.blockContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }
}