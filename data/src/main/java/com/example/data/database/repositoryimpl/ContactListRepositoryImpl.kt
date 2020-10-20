package com.example.data.database.repositoryimpl

import com.example.data.database.dao.ContactDao
import com.example.data.database.mapper.ContactEntityListToContactUiModelListMapper
import com.example.data.database.mapper.ContactEntityListToContactUiModelListMapperWithoutLiveData
import com.example.data.database.mapper.ContactUiModelToContactEntityMapper
import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

class ContactListRepositoryImpl(
    private val contactDao: ContactDao,
    private val contactEntityListToContactUiModelListMapper: ContactEntityListToContactUiModelListMapper,
    private val contactEntityListToContactUiModelListMapperWithoutLiveData: ContactEntityListToContactUiModelListMapperWithoutLiveData,
    private val contactUiModelToContactEntityMapper: ContactUiModelToContactEntityMapper
) : ContactRepository {
    override suspend fun getContactList(isBlocked: Boolean): List<ContactUiModel> {
        return contactEntityListToContactUiModelListMapperWithoutLiveData
            .mapFrom(
                contactDao
                    .getContactList(isBlocked)
            )
    }

    override suspend fun getBlockedContactList(isBlocked: Boolean): List<ContactUiModel> {
        return contactEntityListToContactUiModelListMapperWithoutLiveData
            .mapFrom(
                contactDao
                    .getBlockedContactList(isBlocked)
            )
    }

    override suspend fun addContact(contactUiModel: ContactUiModel) {
        contactDao.addContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }

    override suspend fun updateContact(contactUiModel: ContactUiModel) {
        contactDao.updateContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }

    override suspend fun deleteContact(contactUiModel: ContactUiModel) {
        contactDao.deleteContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }

    override suspend fun blockContact(contactUiModel: ContactUiModel) {
        contactDao.blockContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }

    override suspend fun unblockContact(contactUiModel: ContactUiModel) {
        contactDao.unblockContact(contactUiModelToContactEntityMapper.mapFrom(contactUiModel))
    }
}