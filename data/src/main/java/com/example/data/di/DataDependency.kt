package com.example.data.di

import androidx.room.Room
import com.example.data.database.ContactListDatabase
import com.example.data.database.DATABASE_NAME
import com.example.data.database.mapper.ContactEntityToContactUiMapper
import com.example.data.database.repositoryimpl.ContactListRepositoryImpl
import com.example.domain.repository.ContactListRepository
import org.koin.dsl.module

val dataModule = module {
  //single { (createNetworkClient(get())).create(UserProfileService::class.java) }

  single { ContactEntityToContactUiMapper() }
  single<ContactListRepository> { ContactListRepositoryImpl(get(), get()) }

  single { Room.databaseBuilder(get(), ContactListDatabase::class.java, DATABASE_NAME).build() }

  single { get<ContactListDatabase>().getContactDao() }
}
