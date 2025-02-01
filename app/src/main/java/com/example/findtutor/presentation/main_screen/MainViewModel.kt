package com.example.findtutor.presentation.main_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.findtutor.data.models.LoginState
import com.example.findtutor.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private var _username = mutableStateOf("")
    val username: State<String?> get() = _username
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> get() = _loginState

    init {
        viewModelScope.launch {
            _username.value = fetchUsername().toString()
        }
    }

    private fun fetchUsername() {
        viewModelScope.launch {
            _username.value = getUsernameFromRepository()
        }
    }

    private suspend fun getUsernameFromRepository(): String {
        return authRepository.getUsernameFromFirestore() ?: "Guest"
    }

    fun logout() {
        viewModelScope.launch {
            _loginState.value = LoginState(isLoggedIn = false)
            authRepository.signOut()
        }

    }
}
