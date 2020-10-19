package com.example.data.database.repositoryimpl

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.data.database.dao.ContactDao
import com.example.data.database.mapper.ContactEntityListToContactUiModelListMapper
import com.example.data.database.mapper.ContactUiModelToContactEntityMapper
import com.example.domain.model.ContactUiModel
import com.example.domain.repository.ContactRepository

class ContactListRepositoryImpl(
    private val contactDao: ContactDao,
    private val contactEntityListToContactUiModelListMapper: ContactEntityListToContactUiModelListMapper,
    private val contactUiModelToContactEntityMapper: ContactUiModelToContactEntityMapper
) : ContactRepository {
    override suspend fun getContactList(): LiveData<List<ContactUiModel>> {
        Log.d("apple repo", contactDao
            .getContactList().value?.size.toString())
        return contactEntityListToContactUiModelListMapper
            .mapFrom(
                contactDao
                    .getContactList()
            )
    }

    override suspend fun getBlockedContactList(): LiveData<List<ContactUiModel>> {
        return contactEntityListToContactUiModelListMapper
            .mapFrom(
                contactDao
                    .getBlockedContactList()
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
}