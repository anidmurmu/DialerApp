package com.example.ui.base.adapter

import com.example.ui.base.ViewOnClickListener
import androidx.databinding.ViewDataBinding

abstract class BaseViewHolderBindingFactory {
    abstract fun create(binding: ViewDataBinding, viewType: Int, viewClickCallback: ViewOnClickListener?): BaseBindingViewHolder<out BaseBindingRVModel>
}
