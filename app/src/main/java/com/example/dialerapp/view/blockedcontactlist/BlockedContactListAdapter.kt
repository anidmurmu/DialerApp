package com.example.dialerapp.view.blockedcontactlist

import androidx.appcompat.widget.PopupMenu
import androidx.databinding.ViewDataBinding
import com.example.dialerapp.BR
import com.example.dialerapp.R
import com.example.domain.model.ContactUiModel
import com.example.ui.base.ViewOnClickListener
import com.example.ui.base.adapter.BaseBindingRVModel
import com.example.ui.base.adapter.BaseBindingViewHolder
import com.example.ui.base.adapter.BaseViewHolderBindingFactory
import kotlinx.android.synthetic.main.item_contact.view.*

class BlockedContactListVHFactory : BaseViewHolderBindingFactory() {
    override fun create(
        binding: ViewDataBinding,
        viewType: Int,
        viewClickCallback: ViewOnClickListener?
    ): BaseBindingViewHolder<out BaseBindingRVModel> {
        return BlockedContactListViewHolder(
            binding,
            viewClickCallback
        )
    }
}

class BlockedContactListViewHolder(
    binding: ViewDataBinding,
    private val viewClickCallback: ViewOnClickListener?
) : BaseBindingViewHolder<BlockedContactListRVModel>(binding){
    override fun bind(model: BlockedContactListRVModel) {
        //val contactUiModel = model.getBindingPairs()[0].second as ContactUiModel

        /*itemView.imgBtnMoreOption.setOnClickListener {
            viewClickCallback?.onViewClick(R.id.onclick_item_more, R.id.onclick_item_more)
            val popupMenu = PopupMenu(it.context, it)
            popupMenu.inflate(R.menu.popup_menu_blocked_list)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when(menuItem?.itemId) {
                    R.id.action_popup_delete -> {
                        viewClickCallback?.onViewClick(R.id.onclick_btn_delete, contactUiModel)
                        true
                    }

                    R.id.action_popup_unblock -> {
                        viewClickCallback?.onViewClick(R.id.onclick_btn_unblock, contactUiModel)
                        true
                    }
                }
                false
            }
            popupMenu.show()
        }*/
    }
}

class BlockedContactListRVModel(private val contactUiModel: ContactUiModel) : BaseBindingRVModel {
    override fun getLayoutId(): Int {
        return R.layout.item_blocked_contact
    }

    override fun getBindingPairs(): List<Pair<Int, Any>> {
        return listOf(Pair(BR.bindingVariableContactUiModel, contactUiModel))
    }
}