package com.example.ui.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.ui.R
import okhttp3.Interceptor
import okhttp3.Response

class NetworkMonitorInterceptor(private val context: Context) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    if (!isInternetConnected(context)) {
      throw NoInternetException(context.getString(R.string.no_internet))
    }
    return chain.proceed(chain.request())
  }

  private fun isInternetConnected(context: Context): Boolean {
    val connectivityManager =
      context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val nw = connectivityManager.activeNetwork ?: return false
      val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
      return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
      }
    } else {
      val nwInfo = connectivityManager.activeNetworkInfo ?: return false
      return nwInfo.isConnected
    }
  }
}