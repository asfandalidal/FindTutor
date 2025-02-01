package com.example.findtutor.data.models


sealed class AuthState {
    data object Idle:AuthState()
    data class Success(val message: String) : AuthState()
    data class Failure(val errorMessage: String) : AuthState()
}

data class LoginState(
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null
)

