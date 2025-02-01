package com.example.findtutor.utils

import android.Manifest
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.findtutor.presentation.main_screen.MainScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermissionScreen() {
    val locationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(key1 = true) {
        locationPermissionState.launchPermissionRequest()
    }

    when {
        locationPermissionState.status.isGranted -> {
            // If permission is granted, navigate to the main screen.
            MainScreen(navController = rememberNavController())
        }
        locationPermissionState.status.shouldShowRationale -> {
            // Show an explanation if the user previously denied the permission.
            Text(text = "Location permission is needed to display tutors near you. Please grant the permission.")
        }
        else -> {
            // If the user permanently denies the permission, show an alternative UI.
            Text(text = "Location permission was denied. Please enable it from settings.")
        }
    }
}
