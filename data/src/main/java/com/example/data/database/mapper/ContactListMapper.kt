package com.example.data.database.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.data.database.model.ContactEntity
import com.example.domain.base.Mapper
import com.example.domain.model.ContactUiModel

class ContactEntityListToContactUiModelListMapper :
    Mapper<LiveData<List<ContactEntity>>, LiveData<List<ContactUiModel>>> {
    override fun mapFrom(type: LiveData<List<ContactEntity>>): LiveData<List<ContactUiModel>> {
        val newList: ArrayList<ContactUiModel> = ArrayList()
        return Transformations.map(type) { contactList ->
            contactList.forEach {
                newList.add(
                    ContactUiModel(
                        it.contactNumber,
                        it.fullName,
                        it.email,
                        it.isBlocked
                    )
                )
            }
            return@map newList
        }
    }
}

class ContactEntityListToContactUiModelListMapperWithoutLiveData :
    Mapper<List<ContactEntity>, List<ContactUiModel>> {
    override fun mapFrom(type: List<ContactEntity>): List<ContactUiModel> {
        val newList: ArrayList<ContactUiModel> = ArrayList()
        type.forEach {
            newList.add(
                ContactUiModel(
                    it.contactNumber,
                    it.fullName,
                    it.email,
                    it.isBlocked
                )
            )
        }
        return newList
    }
}

class ContactUiModelToContactEntityMapper : Mapper<ContactUiModel, ContactEntity> {
    override fun mapFrom(type: ContactUiModel): ContactEntity {
        return ContactEntity(
            type.contactNumber,
            type.fullName,
            type.email,
            type.isBlocked
        )
    }
}