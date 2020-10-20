package com.example.dialerapp.view.contactlist

import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.ViewDataBinding
import com.example.dialerapp.App
import com.example.dialerapp.BR
import com.example.dialerapp.R
import com.example.domain.model.ContactUiModel
import com.example.ui.base.ViewOnClickListener
import com.example.ui.base.adapter.BaseBindingRVModel
import com.example.ui.base.adapter.BaseBindingViewHolder
import com.example.ui.base.adapter.BaseViewHolderBindingFactory
import kotlinx.android.synthetic.main.item_contact.view.*

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

class ContactListViewHolder(
    binding: ViewDataBinding,
    private val viewClickCallback: ViewOnClickListener?
) : BaseBindingViewHolder<ContactListRVModel>(binding){
    override fun bind(model: ContactListRVModel) {
        val contactUiModel = model.getBindingPairs()[0].second as ContactUiModel

        itemView.viewListener.setOnClickListener {
            viewClickCallback?.onViewClick(R.id.onclick_item_call, contactUiModel.contactNumber)
        }

        itemView.imgBtnMoreOption.setOnClickListener {
            viewClickCallback?.onViewClick(R.id.onclick_item_more, R.id.onclick_item_more)
            val popupMenu = PopupMenu(it.context, it)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when(menuItem?.itemId) {
                    R.id.action_popup_delete -> {
                        viewClickCallback?.onViewClick(R.id.onclick_btn_delete, contactUiModel)
                        true
                    }

                    R.id.action_popup_block -> {
                        viewClickCallback?.onViewClick(R.id.onclick_btn_block, contactUiModel)
                        true
                    }
                }
                false
            }
            popupMenu.show()
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