package com.example.findtutor.presentation.auth.student.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findtutor.data.models.AuthState
import com.example.findtutor.data.models.LoginState
import com.example.findtutor.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _greetingMessage = mutableStateOf("")
    val greetingMessage: State<String> get() = _greetingMessage

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    var authState: StateFlow<AuthState> = _authState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> get() = _loginState

    init {
        viewModelScope.launch {
            _greetingMessage.value = fetchUsernameAndSetGreeting()
        }
    }

    fun signUp(email: String, password: String, username: String) = viewModelScope.launch {
        _isLoading.value = true
        val result = authRepository.signUpWithEmailAndPassword(email, password)
        if (result.isSuccess) {
            _loginState.value = LoginState(isLoggedIn = true) //temp
            val verificationResponse = authRepository.sendVerificationLink()
            if (verificationResponse is AuthState.Success) {
                _isLoading.value = false
                authRepository.saveUsernameToFirestore(username)
                Log.d("AuthViewModel", "Username saved to Firestore, fetching greeting...")
                _authState.value = verificationResponse
            } else {
                _isLoading.value = false
                _authState.value = verificationResponse
            }
        } else {
            _isLoading.value = false
            _authState.value =
                AuthState.Failure(result.exceptionOrNull()?.message ?: "Account creation failed.")
        }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _isLoading.value = true

        val result = authRepository.signInWithEmailAndPassword(email, password)

        if (result.isSuccess) {
                _loginState.value = LoginState(isLoggedIn = true)
                _isLoading.value = false
                fetchUsernameAndSetGreeting()
                _authState.value = AuthState.Success("Login successful!")
            } else if(result.isFailure) {
                // Handle case where email is not verified
                _isLoading.value = false
                _loginState.value = LoginState(errorMessage = "Email not verified. Please check your inbox.")
                _authState.value = AuthState.Failure("Email not verified. Please check your inbox for the verification link.")
            }
          else {
            // Handle case where credentials don't match
            _isLoading.value = false
            _loginState.value = LoginState(errorMessage = "Incorrect email or password. Please try again.")
            _authState.value = AuthState.Failure(result.exceptionOrNull()?.message ?: "Login failed")
        }
    }
    fun signUpWithGoogle(idToken: String) = viewModelScope.launch {
        _isLoading.value = true
        val result = authRepository.signUpWithGoogle(idToken)
        if (result.isSuccess) {
            _loginState.value = LoginState(isLoggedIn = true)
            _isLoading.value = false
            _authState.value = AuthState.Success("Sign-up successful with Google!")
        } else {
            _authState.value =
                AuthState.Failure(result.exceptionOrNull()?.message ?: "Google sign-up failed.")
        }
    }
    fun loginWithGoogle(idToken: String) = viewModelScope.launch {
        _isLoading.value = true
        val result = authRepository.signUpWithGoogle(idToken)
        if (result.isSuccess) {
            _isLoading.value = false
            _authState.value = AuthState.Success("Login successful with Google!")
        } else {
            _authState.value =
                AuthState.Failure(result.exceptionOrNull()?.message ?: "Google login failed.")
        }
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }

    fun resetPasswordEmail(email: String) = viewModelScope.launch {
        _isLoading.value = true
        val result = authRepository.sendPasswordResetEmail(email)
        if (result.isSuccess) {
            _isLoading.value = false
            _authState.value = AuthState.Success("Password reset email sent")
        } else {
            _authState.value =
                AuthState.Failure(result.exceptionOrNull()?.message ?: "Password reset failed")
        }
    }

    fun saveUsernameToFirestore(username: String) {
        viewModelScope.launch {
            try {
                authRepository.saveUsernameToFirestore(username)
            } catch (e: Exception) {
                Log.e("error", "username not saved ${e.message}")
            }
        }
    }

    private suspend fun fetchUsernameAndSetGreeting(): String {
        val username = authRepository.getUsernameFromFirestore() ?: "Guest"
        val greeting = "Hello, $username 👋 | ${getGreetingMessage()}"
        return greeting
    }

    private fun getGreetingMessage(): String {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (currentHour) {
            in 5..11 -> "Good Morning ☀️"
            in 12..17 -> "Good Afternoon 🌞"
            in 18..20 -> "Good Evening 🌆"
            else -> "Good Night 🌙"
        }
    }
}