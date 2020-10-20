package com.example.dialerapp.view.contactlist

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dialerapp.R
import com.example.dialerapp.databinding.FragmentContactListBinding
import com.example.ui.base.DataBindingBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListFragment : DataBindingBaseFragment<FragmentContactListBinding>() {

    private val model: ContactListViewModel by viewModel()

    override val layoutResource: Int
        get() = R.layout.fragment_contact_list

    override fun onViewDataBindingCreated(binding: FragmentContactListBinding) {
        binding.viewModel = model
        model.init()
        //model.getContactList()

        model.contactListLiveData.value?.liveDataContactScreen?.observe(this, Observer {
            if (it) {
                //model.getContactList()
                findNavController().navigate(R.id.action_contactListFragment_to_contactInfoFragment)
            }
        })
    }

    override fun setBaseStates() {
    }
}