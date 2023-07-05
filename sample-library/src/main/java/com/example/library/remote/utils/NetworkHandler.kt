package com.example.library.remote.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Checks if a network connection exists.
 */
@Suppress("DEPRECATION")
class NetworkHandler(private val context: Context) {

    fun hasNetworkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

}