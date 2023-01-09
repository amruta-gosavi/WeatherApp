package com.mvvm.weather.data.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * checks if internet connection is available
 */
class ConnectionUtils(private val context: Context) {

    fun isConnectedToInternet(): Boolean {
        val connectivity = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val info = connectivity.activeNetworkInfo
        if (info != null) {
            if (info.isConnected) {
                return true
            }
        }

        return false
    }
}