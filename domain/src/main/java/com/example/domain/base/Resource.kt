package com.example.domain.base

import androidx.annotation.NonNull

class Resource<T> constructor(val status: Status, val data: T?, val error: Throwable? = null) {

  companion object {

    fun <T> success(@NonNull data: T): Resource<T> {
      return Resource(Status.SUCCESS, data)
    }

    fun <T> error(throwable: Throwable): Resource<T> {
      return Resource(status = Status.ERROR, data = null, error = throwable)
    }

    fun <T> loading(): Resource<T> = Resource(Status.LOADING, null)
  }

  fun transformStatus(): Status {
    if (error is NoInternetException) {
      return Status.INTERNET_ERROR
    }

    if(data != null ) return Status.RESET_ERROR_LOADER

    return status
  }
}