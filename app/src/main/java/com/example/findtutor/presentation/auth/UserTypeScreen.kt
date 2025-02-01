package com.example.findtutor.presentation.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.findtutor.R
import com.example.findtutor.presentation.auth.components.TopBar
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsBold
import com.example.findtutor.utils.ExitConfirmationDialog

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
        border = BorderStroke(width = 2.dp, color = Color.White)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseUserType(navController: NavController) {
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    BackHandler(
        onBack = {
            showDialog.value = true
        }
    )
    Scaffold(
        containerColor = PrimaryColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Choose User Type",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsBold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryColor)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Image
                Image(
                    painter = painterResource(id = R.drawable.select),
                    contentDescription = "User Type",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(top = 32.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Buttons
                Text(
                    text = "Please select your role to proceed",
                    color = PrimaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(42.dp))

                CustomButton("Join as a Student", onClick = {navController.navigate("signup")})
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton("Join as a Tutor", onClick = {})
            }
        }
    )
    ExitConfirmationDialog(showDialog = showDialog,
        onExit = {
            (context as? android.app.Activity)?.finish()
        })
}