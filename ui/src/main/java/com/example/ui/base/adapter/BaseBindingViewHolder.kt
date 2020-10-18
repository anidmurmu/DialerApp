package com.example.ui.base.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

open class BaseBindingViewHolder<T : BaseBindingRVModel>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root), LayoutContainer {

    override val containerView: View?
        get() = itemView

    open fun bind(model: T) {
    }

    open fun onViewRecycled() {
    }

    open fun onBindWithPayload(model: T, payloads: List<Any>) {
        if (isAutoBind()) {
            (model as BaseBindingRVModel).let {
                for (pair in it.getBindingPairs()) {
                    binding.setVariable(pair.first, pair.second)
                }
            }
        }
        bind(model)
    }

    open fun isAutoBind() = true
}
