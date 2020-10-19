package com.example.data.database.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.data.database.model.ContactEntity
import com.example.domain.base.Mapper
import com.example.domain.model.ContactUiModel

class ContactEntityToContactUiMapper : Mapper<LiveData<List<ContactEntity>>, LiveData<List<ContactUiModel>>> {
  override fun mapFrom(type: LiveData<List<ContactEntity>>): LiveData<List<ContactUiModel>> {
    val newList: ArrayList<ContactUiModel> = ArrayList()
    return Transformations.map(type) { contactList ->
      contactList.forEach {
        newList.add(ContactUiModel(it.name))
      }
      return@map newList
    }
  }
}