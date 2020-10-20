package com.example.dialerapp.view.dialer

import androidx.lifecycle.Observer
import com.example.dialerapp.MainActivity
import com.example.dialerapp.R
import com.example.dialerapp.databinding.FragmentDialerBinding
import com.example.ui.base.DataBindingBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialerFragment : DataBindingBaseFragment<FragmentDialerBinding>() {

    private val model: DialerViewModel by viewModel()

    override val layoutResource: Int
        get() = R.layout.fragment_dialer

    override fun onViewDataBindingCreated(binding: FragmentDialerBinding) {
        binding.viewModel = model
        model.init()

        model.liveDataDialerViewState.value?.liveDataMakeCall?.observe(this, Observer {
            val phoneNumber = model.liveDataDialerViewState.value?.contactNumber?.value ?: ""
            (activity as MainActivity).makePhoneCall(phoneNumber)
        })
    }

    override fun setBaseStates() {
    }
}