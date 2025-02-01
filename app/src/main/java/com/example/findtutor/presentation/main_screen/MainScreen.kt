package com.example.findtutor.presentation.main_screen


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.findtutor.presentation.auth.student.viewmodel.AuthViewModel
import com.example.findtutor.presentation.main_screen.components.BrowseTutorsUI
import com.example.findtutor.presentation.main_screen.components.LocationSelectionUI
import com.example.findtutor.ui.theme.poppinsBold
import com.example.findtutor.ui.theme.poppinsRegular
import com.example.findtutor.utils.ExitConfirmationDialog

@Composable
fun MainScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {

    val greetingMessage by viewModel.greetingMessage
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    BackHandler {
        showDialog.value = true
    }
    val splitGreeting = greetingMessage.split("|", limit = 2)
    val greetingText = splitGreeting.getOrNull(0)?.trim() ?: "Hello 👋"
    val additionalGreeting = splitGreeting.getOrNull(1)?.trim() ?: ""


    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF3D7CFF),
                                Color(0xFF0042A0)
                            )
                        )
                    )
                    .padding(16.dp)
//                    .padding(top = 15.dp)
            ) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    Text(
                        text =  greetingText,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontFamily = poppinsBold
                        ),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = additionalGreeting,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            fontFamily = poppinsRegular
                        )
                    )
                }
            }
        },

        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(contentPadding)
            ) {
                LocationSelectionUI(navController)
                Spacer(modifier = Modifier.height(16.dp))
                BrowseTutorsUI(navController)
            }
        }
    )
    ExitConfirmationDialog(showDialog = showDialog,
        onExit = {
            (context as? android.app.Activity)?.finish()
        })
}
