package com.example.dialerapp.view.blockedcontactlist

import com.example.dialerapp.R
import com.example.dialerapp.databinding.FragmentBlockedContactListBinding
import com.example.ui.base.DataBindingBaseFragment
import com.example.ui.base.adapter.RVModelBindingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlockedContactListFragment : DataBindingBaseFragment<FragmentBlockedContactListBinding>() {
    private val model: BlockedContactListViewModel by viewModel()

    override val layoutResource: Int
        get() = R.layout.fragment_blocked_contact_list

    override fun onViewDataBindingCreated(binding: FragmentBlockedContactListBinding) {
        binding.viewModel = model
        model.init()
        model.getBlockedContactList()

        binding.recyclerBlockedContactList.adapter = RVModelBindingAdapter(
            emptyList(),
            model,
            BlockedContactListVHFactory()
        )

    }

    override fun setBaseStates() {

    }
}