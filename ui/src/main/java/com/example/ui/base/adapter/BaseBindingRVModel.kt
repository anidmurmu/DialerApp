package com.example.ui.base.adapter

import androidx.annotation.LayoutRes

interface BaseBindingRVModel {
    @LayoutRes
    fun getLayoutId(): Int

    // this will take the list of binding pairs where each pair contains the generated binding id
    // and its corresponding model (BR.{},Model)
    fun getBindingPairs(): List<Pair<Int, Any>>
}
