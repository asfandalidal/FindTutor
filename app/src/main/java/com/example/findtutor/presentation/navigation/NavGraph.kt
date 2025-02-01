package com.example.findtutor.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.example.findtutor.R
import com.example.findtutor.presentation.auth.ChooseUserType
import com.example.findtutor.presentation.auth.SplashScreen
import com.example.findtutor.presentation.auth.student.login.LoginScreen
import com.example.findtutor.presentation.auth.student.signup.SignUpScreen
import com.example.findtutor.presentation.main_screen.MainScreen
import com.example.findtutor.presentation.main_screen.profile.AboutTutorFinderScreen
import com.example.findtutor.presentation.main_screen.profile.ContactUsScreen
import com.example.findtutor.presentation.main_screen.profile.PrivacyPolicyScreen
import com.example.findtutor.presentation.main_screen.profile.ProfileScreen
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.utils.isBottomBarVisible
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.findtutor.presentation.auth.teacher.registration.TeacherListScreen
import com.example.findtutor.presentation.auth.teacher.registration.TeacherRegistrationScreen
import com.example.findtutor.presentation.find_tutor.FindTutorScreen


val bottomNavigationItems = listOf(
    SmoothAnimationBottomBarScreens(
        route = "main_screen",
        name = "Home",
        icon = R.drawable.home_icon
    ),
    SmoothAnimationBottomBarScreens(
        route = "teacher_list",
        name = "Find",
        icon = R.drawable.find_users_icon
    ),
    SmoothAnimationBottomBarScreens(
        route = "profile_screen",
        name = "Profile",
        icon = R.drawable.user_icon
    ),

)

@Composable
fun NavGraph(navController: NavHostController) {
    val currentIndex = rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible(navController)) {
                Box(modifier = Modifier.offset(y = (-40).dp)) {
                    SmoothAnimationBottomBar(
                        navController = navController,
                        bottomNavigationItems = bottomNavigationItems,
                        initialIndex = currentIndex,
                        bottomBarProperties = BottomBarProperties(
                            backgroundColor = PrimaryColor,
                            indicatorColor = Color.White.copy(alpha = 0.2F),
                            iconTintColor = Color.White,
                            iconTintActiveColor = Color.White,
                            textActiveColor = Color.White,
                            cornerRadius = 18.dp,
                            fontSize = 14.sp
                        ),
                        onSelectItem = { selectedItem ->
                            if (selectedItem.route != navController.currentDestination?.route) {
                                navController.navigate(selectedItem.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") { SplashScreen(navController = navController) }
                composable("choose_type") { ChooseUserType(navController = navController) }
                composable("login") { LoginScreen(navController = navController) }
                composable("signup") { SignUpScreen(navController = navController) }
                composable("main_screen") { MainScreen(navController = navController) }
                composable("profile_screen") { ProfileScreen(navController = navController) }
                composable("about_tutor_finder_screen") {
                    AboutTutorFinderScreen(navController)
                }
                composable("register_tutor")
                {
                    TeacherRegistrationScreen(navController)
                }
                composable(
                    route = "teacher_list",
                ) {
                    TeacherListScreen(navController = navController, location = "Mirpurkhas")
                }


                composable("terms") { PrivacyPolicyScreen(navController = navController) }
                composable("contact_us") { ContactUsScreen(navController = navController) }
            }
        }
    }
}
