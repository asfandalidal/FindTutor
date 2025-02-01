package com.example.findtutor.presentation.auth.student.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.findtutor.BuildConfig
import com.example.findtutor.R
import com.example.findtutor.presentation.auth.components.*
import com.example.findtutor.presentation.auth.student.viewmodel.AuthViewModel
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsRegular
import com.example.findtutor.utils.ExitConfirmationDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: AuthViewModel = hiltViewModel(), navController: NavController) {

    var emailAddress by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var rememberMeChecked by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    BackHandler {
        showDialog.value = true
    }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }

    //observing login state
    val loginState by viewModel.loginState.collectAsState()
    //observing loading state
    var isLoading = viewModel.isLoading
    val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.web_client_id))
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken
            if (idToken != null) {
                viewModel.signUpWithGoogle(idToken)
            }
        } catch (e: ApiException) {
            Log.e("LoginScreen", "Google login failed", e)
            scope.launch {
                snackbarHostState.showSnackbar("Google login failed")
            }
        }
    }

    // Email validation function
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Display Snackbar function
    fun showSnackbar(scope: CoroutineScope, snackbarHostState: SnackbarHostState, message: String) {
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    LaunchedEffect(loginState) {
        if (loginState.isLoggedIn) {
            navController.navigate("main_screen") {
                popUpTo("login") { inclusive = true }
            }
        } else if (loginState.errorMessage != null) {
            snackbarHostState.showSnackbar(loginState.errorMessage!!)
        }
    }

    Scaffold(
        topBar = {
            TopBar("Find Tutor", modifier = Modifier.fillMaxWidth(), onBackClick = { navController.navigateUp() })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize().background(color = PrimaryColor)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = PrimaryColor)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Login to Your Account",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsRegular
                ),
                modifier = Modifier.padding(top = 20.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                value = emailAddress,
                onValueChange = { newEmailAddress ->
                    emailAddress = newEmailAddress
                },
                isValid = isValidEmail(emailAddress),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                hint = "Email Address",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(25.dp))

            CustomPasswordField(
                value = password,
                onValueChange = { password = it },
                isValid = true,
                hint = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                passwordVisible = passwordVisible,
                onVisibilityToggle = { passwordVisible = !passwordVisible },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth().offset(x = (-8).dp)
            ) {
                Checkbox(
                    checked = rememberMeChecked,
                    onCheckedChange = { rememberMeChecked = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color.White, uncheckedColor = Color.LightGray)
                )
                Text(
                    text = "Remember Me",
                    style = TextStyle(fontFamily = poppinsRegular, fontSize = 14.sp, color = Color.White)
                )
            }

            CustomButton(
                text = "Login",
                onClick = {
                    if (isValidEmail(emailAddress)) {
                        if (emailAddress.isEmpty() || password.isEmpty()) {
                            showSnackbar(scope, snackbarHostState, "Enter your valid credentials")
                        } else {
                            viewModel.signIn(emailAddress, password)
                        }
                    } else {
                        showSnackbar(scope, snackbarHostState, "Please enter valid email")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            if (isLoading.value) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.padding(3.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = {
                if (emailAddress.isEmpty()) {
                    showSnackbar(scope, snackbarHostState, "Please enter your email to reset password")
                } else {
                    viewModel.resetPasswordEmail(emailAddress)
                    showSnackbar(scope, snackbarHostState, "Password reset email sent")
                }
            }) {
                Text(
                    text = "Forgot Password?",
                    style = TextStyle(
                        fontFamily = poppinsRegular,
                        fontSize = 16.sp,
                        color = Color.White,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(color = Color.White.copy(alpha = 0.5f), modifier = Modifier.weight(1f))
                Text(
                    text = "OR SIGN IN WITH",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsRegular
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color.White.copy(alpha = 0.5f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            GoogleSignInButton(onClick = {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            })

            TextButton(onClick = { navController.navigate("signup") }) {
                Text(
                    text = "Don't have an account? Register",
                    style = TextStyle(
                        fontFamily = poppinsRegular,
                        fontSize = 16.sp,
                        color = Color.White,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
        ExitConfirmationDialog(
            showDialog = showDialog,
            onExit = {
                (context as? android.app.Activity)?.finish()
            }
        )

    }
}
