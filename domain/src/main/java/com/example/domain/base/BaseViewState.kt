package com.example.domain.base

abstract class BaseViewState<T>(val status: Status, val error: Throwable? = null, val data: T?) {
  fun isLoading() = status == Status.LOADING

  fun getErrorMessage() = error?.message

  fun shouldShowErrorMessage() = error != null
}