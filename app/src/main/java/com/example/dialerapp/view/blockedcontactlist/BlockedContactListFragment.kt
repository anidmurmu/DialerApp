package com.example.dialerapp.view.blockedcontactlist

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dialerapp.R
import com.example.dialerapp.databinding.FragmentBlockedContactListBinding
import com.example.ui.base.DataBindingBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlockedContactListFragment : DataBindingBaseFragment<FragmentBlockedContactListBinding>() {
    private val model: BlockedContactListViewModel by viewModel()

    override val layoutResource: Int
        get() = R.layout.fragment_blocked_contact_list

    override fun onViewDataBindingCreated(binding: FragmentBlockedContactListBinding) {
        binding.viewModel = model
        model.init()

        model.getBlockedContactList()

        model.blockedContactListViewStateLiveData?.value?.liveDataBlockedContactScreen?.observe(this, Observer {
            if (it) {
                findNavController().navigate(R.id.action_contactListFragment_to_blockedContactListFragment)
            }
        })
    }

    override fun setBaseStates() {

    }
}