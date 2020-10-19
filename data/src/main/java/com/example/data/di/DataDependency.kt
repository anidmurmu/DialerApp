package com.example.data.di

import androidx.room.Room
import com.example.data.database.ContactListDatabase
import com.example.data.database.DATABASE_NAME
import com.example.data.database.mapper.ContactEntityListToContactUiModelListMapper
import com.example.data.database.mapper.ContactUiModelToContactEntityMapper
import com.example.data.database.repositoryimpl.ContactListRepositoryImpl
import com.example.domain.repository.ContactRepository
import org.koin.dsl.module

val dataModule = module {
  //single { (createNetworkClient(get())).create(UserProfileService::class.java) }

  single { ContactEntityListToContactUiModelListMapper() }
  single { ContactUiModelToContactEntityMapper() }
  single<ContactRepository> { ContactListRepositoryImpl(get(), get(), get()) }

  single { Room.databaseBuilder(get(), ContactListDatabase::class.java, DATABASE_NAME).build() }

  single { get<ContactListDatabase>().getContactDao() }
}
