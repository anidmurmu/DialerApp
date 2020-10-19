package com.example.domain.di

import com.example.domain.usecase.GetContactListUseCase
import com.example.domain.usecase.GetContactListUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
  single<GetContactListUseCase> { GetContactListUseCaseImpl(get()) }
}