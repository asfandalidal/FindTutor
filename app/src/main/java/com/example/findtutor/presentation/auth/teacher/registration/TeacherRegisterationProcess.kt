package com.example.findtutor.presentation.auth.teacher.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsBold
import com.example.findtutor.ui.theme.poppinsRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherRegistrationScreen(navController: NavController) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var qualification by rememberSaveable { mutableStateOf("") }
    var subjects by rememberSaveable { mutableStateOf("") }
    var classes by rememberSaveable { mutableStateOf("") }
    var experience by rememberSaveable { mutableStateOf("") }
    var fees by rememberSaveable { mutableStateOf("") }
    var contactNumber by rememberSaveable { mutableStateOf("") }
    var area by rememberSaveable { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Teacher Registration",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsBold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryColor),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White), // Match profile screen background
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Register as a Teacher",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = PrimaryColor,
                            fontFamily = poppinsBold
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Form Fields with updated styling
                    CustomTextField(value = fullName, onValueChange = { fullName = it }, hint = "Full Name")
                    CustomTextField(value = email, onValueChange = { email = it }, hint = "Email Address", keyboardType = KeyboardType.Email)
                    CustomPasswordField(value = password, onValueChange = { password = it }, hint = "Password")
                    CustomTextField(value = qualification, onValueChange = { qualification = it }, hint = "Qualification")
                    CustomTextField(value = subjects, onValueChange = { subjects = it }, hint = "Subjects (comma separated)")
                    CustomTextField(value = classes, onValueChange = { classes = it }, hint = "Classes You Teach")
                    CustomTextField(value = experience, onValueChange = { experience = it }, hint = "Years of Experience", keyboardType = KeyboardType.Number)
                    CustomTextField(value = fees, onValueChange = { fees = it }, hint = "Monthly Fee Requirement", keyboardType = KeyboardType.Number)
                    CustomTextField(value = contactNumber, onValueChange = { contactNumber = it }, hint = "WhatsApp Number", keyboardType = KeyboardType.Phone)
                    CustomTextField(value = area, onValueChange = { area = it }, hint = "Area of Home Tuition")

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomButton(
                        text = "Register",
                        onClick = { /* Handle registration */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = hint,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = poppinsRegular
                )
            )
        },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 14.sp,
            fontFamily = poppinsRegular
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = Color.LightGray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(value: String, onValueChange: (String) -> Unit, hint: String) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = hint,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = poppinsRegular
                )
            )
        },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 14.sp,
            fontFamily = poppinsRegular
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "Toggle password visibility",
                    tint = PrimaryColor
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = Color.LightGray
        )
    )
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor,
            contentColor = Color.White
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = poppinsBold
            ),
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}