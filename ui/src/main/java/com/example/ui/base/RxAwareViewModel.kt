package com.example.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.base.Resource
import com.example.domain.base.Status
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import retrofit2.HttpException

open class RxAwareViewModel : ViewModel(), KoinComponent, ViewOnClickListener {

    val disposable = CompositeDisposable()
    val statusState = MutableLiveData<Status>(Status.SUCCESS)

    protected fun showProgress(){
        statusState.postValue(Status.LOADING)
    }

    protected fun hideProgress(){
        statusState.postValue(Status.SUCCESS)
    }

    protected fun showInternetError(){
        statusState.postValue(Status.INTERNET_ERROR)
    }

    protected fun hideError(){
        statusState.postValue(Status.RESET_ERROR_LOADER)
    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }

    fun getResponseCode(resource: Resource<*>): Int {
        return if (resource.error is HttpException) {
            (resource.error as HttpException).code()
        } else 900
    }

    override fun onViewClick(id: Int, data: Any) {

    }
}
