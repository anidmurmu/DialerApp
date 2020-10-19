package com.example.dialerapp.view.contactaddition

import com.example.dialerapp.R
import com.example.dialerapp.databinding.FragmentContactInfoBinding
import com.example.ui.base.DataBindingBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactInfoFragment : DataBindingBaseFragment<FragmentContactInfoBinding>() {
    private val model: ContactInfoViewModel by viewModel()

    override val layoutResource: Int
        get() = R.layout.fragment_contact_info

    override fun onViewDataBindingCreated(binding: FragmentContactInfoBinding) {
        binding.viewModel = model
    }

    override fun setBaseStates() {

    }
}