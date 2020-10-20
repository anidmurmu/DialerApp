package com.example.domain.di

import com.example.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
  single<GetContactListUseCase> { GetContactListUseCaseImpl(get()) }
  single<GetBlockedContactListUseCase> { GetBlockedContactListUseCaseImpl(get()) }
  single<AddContactUseCase> { AddContactUseCaseImpl(get()) }
  single<UpdateContactUseCase> { UpdateContactUseCaseImpl(get()) }
  single<DeleteContactUseCase> { DeleteContactUseCaseImpl(get()) }
  single<BlockContactUseCase> { BlockContactUseCaseImpl(get()) }
  single<UnblockContactUseCase> { UnblockContactUseCaseImpl(get()) }
}