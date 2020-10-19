package com.example.dialerapp.view

import com.example.dialerapp.R
import com.example.dialerapp.databinding.FragmentContactListBinding
import com.example.ui.base.DataBindingBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListFragment : DataBindingBaseFragment<FragmentContactListBinding>() {

    private val model : ContactListViewModel by viewModel()

    override val layoutResource: Int
        get() = R.layout.fragment_contact_list

    override fun onViewDataBindingCreated(binding: FragmentContactListBinding) {
        binding.viewModel = model
    }

    override fun setBaseStates() {
    }
}