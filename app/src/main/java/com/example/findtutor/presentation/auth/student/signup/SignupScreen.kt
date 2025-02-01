package com.example.findtutor.presentation.auth.student.signup

import android.content.res.Configuration
import android.util.Patterns
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.findtutor.R
import com.example.findtutor.core.ConnectivityStatusChecker
import com.example.findtutor.core.NoInternetScreen
import com.example.findtutor.data.models.AuthState
import com.example.findtutor.presentation.auth.components.*
import com.example.findtutor.presentation.auth.student.viewmodel.AuthViewModel
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsBold
import com.example.findtutor.ui.theme.poppinsRegular
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(viewModel: AuthViewModel = hiltViewModel(), navController: NavController) {

    var username by rememberSaveable { mutableStateOf("") }
    var isValidUsername by rememberSaveable { mutableStateOf(true) }
    var emailAddress by rememberSaveable { mutableStateOf("") }
    var isValidEmail by rememberSaveable { mutableStateOf(true) }
    var password by rememberSaveable { mutableStateOf("") }
    var isValidPassword by rememberSaveable { mutableStateOf(false) }
    val passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isConnected by remember { mutableStateOf(true) }

    ConnectivityStatusChecker(onConnectionChanged = { connected -> isConnected = connected })

    val isLoading = viewModel.isLoading
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    fun validateUsername() { isValidUsername = username.length > 3 }
    fun validateEmail() { isValidEmail = Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches() }
    fun validatePassword() {
        val regex = "^(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+\$).{6,}\$"
        isValidPassword = password.matches(regex.toRegex())
    }

    if (isConnected) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "TutorFinder",
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
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = PrimaryColor) // Ensure full screen is covered
                    .padding(contentPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()), // Ensure scrolling for small screens
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignUpFormContent(
                        username = username,
                        emailAddress = emailAddress,
                        password = password,
                        isValidUsername = isValidUsername,
                        isValidEmail = isValidEmail,
                        isValidPassword = isValidPassword,
                        validateUsername = { validateUsername() },
                        validateEmail = { validateEmail() },
                        validatePassword = { validatePassword() },
                        showSnackbar = { message ->
                            scope.launch { snackbarHostState.showSnackbar(message) }
                        },
                        viewModel = viewModel,
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        isLoading = isLoading
                    )
                }
            }
        }
    } else {
        NoInternetScreen()
    }
}


@Composable
fun SignUpFormContent(
    username: String, emailAddress: String, password: String,
    isValidUsername: Boolean, isValidEmail: Boolean, isValidPassword: Boolean,
    validateUsername: () -> Unit, validateEmail: () -> Unit, validatePassword: () -> Unit,
    showSnackbar: (String) -> Unit,
    viewModel: AuthViewModel, navController: NavController,
    snackbarHostState: SnackbarHostState, isLoading: State<Boolean>
) {
    Text(
        text = "Create an Account",
        style = TextStyle(
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsRegular
        ),
//        modifier = Modifier.padding(top = 20.dp)
    )

    Spacer(modifier = Modifier.height(2.dp))

    CustomTextField(
        value = username,
        onValueChange = {
//            username = it
            validateUsername()
        },
        isValid = isValidUsername,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        hint = "Full Name",
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(25.dp))

    CustomTextField(
        value = emailAddress,
        onValueChange = {
//            emailAddress = it
            validateEmail()
        },
        isValid = isValidEmail,
        hint = "Email Address",
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(25.dp))

    CustomPasswordField(
        value = password,
        onValueChange = {
//            password = it
            validatePassword()
        },
        isValid = isValidPassword,
        hint = "Password",
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        passwordVisible = false,
        onVisibilityToggle = { },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(25.dp))

    CustomButton(
        text = "Register",
        onClick = {
            if (username.isEmpty() || emailAddress.isEmpty() || password.isEmpty()) {
                showSnackbar("Fill all the fields")
            } else {
                viewModel.signUp(emailAddress, password, username)
            }
        },
        modifier = Modifier.fillMaxWidth()
    )

    if (isLoading.value) {
        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier.padding(3.dp)
        )
    }

    Spacer(modifier = Modifier.height(25.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "OR SIGN UP WITH",
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsRegular
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Divider(
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(modifier = Modifier.height(25.dp))
    GoogleSignInButton(onClick = { /* Launch Google Sign-In */ })

    Spacer(modifier = Modifier.height(10.dp))

    TextButton(onClick = { navController.navigate("login") }) {
        Text(
            text = "Already have an account? Login",
            style = TextStyle(
                fontFamily = poppinsRegular,
                fontSize = 16.sp,
                color = Color.White,
                textDecoration = TextDecoration.Underline
            ),
        )
    }
}
