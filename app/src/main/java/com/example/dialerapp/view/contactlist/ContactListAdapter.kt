package com.example.dialerapp.view.contactlist

import androidx.databinding.ViewDataBinding
import com.example.dialerapp.BR
import com.example.dialerapp.R
import com.example.domain.model.ContactUiModel
import com.example.ui.base.ViewOnClickListener
import com.example.ui.base.adapter.BaseBindingRVModel
import com.example.ui.base.adapter.BaseBindingViewHolder
import com.example.ui.base.adapter.BaseViewHolderBindingFactory

class ContactListVHFactory : BaseViewHolderBindingFactory() {
    override fun create(
        binding: ViewDataBinding,
        viewType: Int,
        viewClickCallback: ViewOnClickListener?
    ): BaseBindingViewHolder<out BaseBindingRVModel> {
        return ContactListViewHolder(
            binding,
            viewClickCallback
        )
    }
}

class ContactListViewHolder(binding: ViewDataBinding, private val viewClickCallback: ViewOnClickListener?) : BaseBindingViewHolder<ContactListRVModel>(binding) {
    override fun bind(model: ContactListRVModel) {
        val contactUiModel = model.getBindingPairs()[0].second as ContactUiModel

        itemView.setOnClickListener {
            viewClickCallback?.onViewClick(R.id.onclick_item_call, contactUiModel.contactNumber)
        }
    }
}

class ContactListRVModel(private val contactUiModel: ContactUiModel) : BaseBindingRVModel {
    override fun getLayoutId(): Int {
        return R.layout.item_contact
    }

    override fun getBindingPairs(): List<Pair<Int, Any>> {
        return listOf(Pair(BR.bindingVariableContactUiModel, contactUiModel))
    }
}