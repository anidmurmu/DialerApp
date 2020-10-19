package com.example.dialerapp.di

import com.example.dialerapp.view.ContactListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ContactListViewModel() }
}