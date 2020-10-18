package com.example.dialerapp

import android.app.Application
import com.example.data.di.dataModule
import com.example.dialerapp.di.appModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@App)
      modules(listOf(appModule, dataModule, domainModule))
    }

    instance = this
    setupNetwork()
  }

  private fun setupNetwork() {
    //createNetworkClient(this)
  }

  companion object {
    lateinit var instance: App
  }
}
