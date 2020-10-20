package com.example.dialerapp.view.dialer

import com.example.dialerapp.R
import com.example.dialerapp.databinding.FragmentDialerBinding
import com.example.dialerapp.view.contactlist.ContactListViewModel
import com.example.ui.base.DataBindingBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialerFragment : DataBindingBaseFragment<FragmentDialerBinding>() {

    private val model: DialerViewModel by viewModel()

    override val layoutResource: Int
        get() = R.layout.fragment_dialer

    override fun onViewDataBindingCreated(binding: FragmentDialerBinding) {
        model.init()
    }

    override fun setBaseStates() {
    }
}