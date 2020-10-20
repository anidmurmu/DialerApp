package com.example.dialerapp.di

import com.example.dialerapp.view.contactaddition.ContactInfoViewModel
import com.example.dialerapp.view.contactlist.ContactListViewModel
import com.example.dialerapp.view.dialer.DialerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ContactListViewModel(get(), get(), get(), get()) }
    viewModel { ContactInfoViewModel(get(), get()) }
    viewModel { DialerViewModel() }
}