package com.example.ui.bindings

import com.example.ui.base.adapter.RVModelBindingAdapter
import android.view.View
import com.example.ui.base.adapter.BaseBindingRVModel
import com.example.ui.base.adapter.BaseRVBindingAdapter
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("data")
fun <T : BaseBindingRVModel> setRecyclerViewProperties(recyclerView: RecyclerView, data: List<T>?) {
  data?.let {
    if (recyclerView.adapter is BaseRVBindingAdapter<*>) {
      (recyclerView.adapter as RVModelBindingAdapter).setItems(data)
    }
  }
}

@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: String?) {
  Glide.with(view.context)
    .load(imageUrl).apply(RequestOptions().circleCrop())
    .into(view)
}

@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
  view.error = errorMessage
}

@BindingAdapter("visible")
fun setVisibility(view: View, shouldShow: Boolean) {
  view.visibility = if (shouldShow) View.VISIBLE else View.INVISIBLE
}