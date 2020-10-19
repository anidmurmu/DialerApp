package com.example.dialerapp.di

import com.example.dialerapp.view.contactaddition.ContactInfoViewModel
import com.example.dialerapp.view.contactlist.ContactListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ContactListViewModel(get()) }
    viewModel { ContactInfoViewModel() }
}