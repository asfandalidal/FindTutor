package com.example.findtutor.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun ConnectivityStatusChecker(onConnectionChanged: (Boolean) -> Unit) {
    val context = LocalContext.current
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val isConnected = remember { mutableStateOf(false) }

    // Monitor network status changes
    LaunchedEffect(context) {
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isConnected.value = true
                onConnectionChanged(true)
            }

            override fun onLost(network: Network) {
                isConnected.value = false
                onConnectionChanged(false)
            }
        })
    }

    // Initially check for internet availability
    val currentNetwork = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(currentNetwork)
    isConnected.value = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

    // Show the connectivity status
    if (isConnected.value) {
        onConnectionChanged(true)
    } else {
        onConnectionChanged(false)
    }
}
