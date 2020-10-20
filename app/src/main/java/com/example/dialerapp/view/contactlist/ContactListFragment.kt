package com.example.dialerapp.view.contactlist

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dialerapp.MainActivity
import com.example.dialerapp.R
import com.example.dialerapp.databinding.FragmentContactListBinding
import com.example.ui.base.DataBindingBaseFragment
import com.example.ui.base.adapter.RVModelBindingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListFragment : DataBindingBaseFragment<FragmentContactListBinding>() {

    private val model: ContactListViewModel by viewModel()

    override val layoutResource: Int
        get() = R.layout.fragment_contact_list

    override fun onViewDataBindingCreated(binding: FragmentContactListBinding) {
        binding.viewModel = model
        model.init()

        binding.recyclerContactList.adapter = RVModelBindingAdapter(
            emptyList(),
            model,
            ContactListVHFactory()
        )

        model.contactListLiveData.value?.liveDataBlockedContactScreen?.observe(this, Observer {
            if (it) {
                findNavController().navigate(R.id.action_contactListFragment_to_blockedContactListFragment)
            }
        })
        model.contactListLiveData.value?.liveDataContactScreen?.observe(this, Observer {
            if (it) {
                findNavController().navigate(R.id.action_contactListFragment_to_contactInfoFragment)
            }
        })
        model.contactListLiveData.value?.liveDataDialerScreen?.observe(this, Observer {
            if (it) {
                findNavController().navigate(R.id.action_contactListFragment_to_dialerFragment)
            }
        })

        model.contactListLiveData.value?.liveDataMakeCall?.observe(this, Observer {
            if (it) {
                val phoneNumber = model.contactListLiveData.value?.liveDataPhoneNumber?.value ?: ""
                (activity as MainActivity).makePhoneCall(phoneNumber)
            }
        })
    }

    override fun setBaseStates() {
    }
}