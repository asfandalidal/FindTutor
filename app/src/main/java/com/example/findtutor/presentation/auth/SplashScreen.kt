package com.example.findtutor.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.findtutor.R
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.utils.PreferencesHelper

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    var logoAlpha by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        delay(500)
        logoAlpha = 1f

        delay(2000)
        val isFirstTime = PreferencesHelper.isFirstTimeLaunch(context)
        val isLoggedIn = PreferencesHelper.isLoggedIn(context)

        when {
//            isFirstTime -> {
//                PreferencesHelper.setFirstTimeLaunch(context, false)
//                navController.navigate("choose_type") {
//                    popUpTo("splash") { inclusive = true }
//                }
//            }
            isLoggedIn -> {
                navController.navigate("main_screen") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            else -> {
                navController.navigate("signup") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ft_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(300.dp)
                    .graphicsLayer(alpha = logoAlpha)
            )
        }
    }
}
