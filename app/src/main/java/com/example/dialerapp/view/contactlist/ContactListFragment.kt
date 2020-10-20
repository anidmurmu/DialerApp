package com.example.dialerapp.view.contactlist

import RVModelBindingAdapter
import android.util.Log
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

        binding.recyclerContactList.adapter = RVModelBindingAdapter(
            emptyList(),
            model,
            ContactListVHFactory()
        )
        model.contactListLiveData.value?.liveDataUserContactList?.observe(this, Observer {
            //Log.d("apple list", it.toString())
            it.forEach { model ->
               val contact =  model.getBindingPairs()[0].second
                Log.d("apple content", contact.toString())
            }
            /*binding.recyclerContactList.adapter = RVModelBindingAdapter(
                it,
                model,
                ContactListVHFactory()
            )*/
        })

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