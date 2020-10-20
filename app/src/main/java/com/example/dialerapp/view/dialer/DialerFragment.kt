package com.example.dialerapp.view.dialer

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.Observer
import com.example.dialerapp.App
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
            val phoneNumber = model.liveDataDialerViewState.value?.contactNumber?.value
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phoneNumber")
            if (dialIntent.resolveActivity(App.instance.packageManager) != null) {
                startActivity(dialIntent)
            } else {
                Log.e("apple", "Can't resolve app for ACTION_DIAL Intent.")
            }
        })
    }

    override fun setBaseStates() {
    }
}