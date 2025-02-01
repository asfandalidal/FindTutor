package com.example.findtutor.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun isBottomBarVisible(navController: NavController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    return currentRoute in listOf("main_screen", "teacher_list/Mirpurkhas", "profile_screen")
}

