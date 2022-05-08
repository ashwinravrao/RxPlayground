package com.ashwinrao.rxplayground.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.ashwinrao.rxplayground.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetConnected()) throw IOException(context.getString(R.string.error_network_connection))
        else return chain.proceed(chain.request())
    }

    private fun isInternetConnected(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
        return capabilities?.hasAnyInternetTransport() ?: false
    }
}

fun NetworkCapabilities.hasAnyInternetTransport() =
    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI)