package com.example.kotlinkmm.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email, error = null) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password, error = null) }
    }

    fun onLoginClicked() {
        val currentState = _state.value
        if (currentState.email.isBlank() || currentState.password.isBlank()) {
            _state.update { it.copy(error = "Fields cannot be empty") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            // Mock login delay
            delay(2000)

            if (currentState.email == "test@example.com" && currentState.password == "password") {
                _state.update { it.copy(isLoading = false, isLoggedIn = true) }
            } else {
                _state.update { it.copy(isLoading = false, error = "Invalid email or password") }
            }
        }
    }

    fun onForgotPasswordClicked() {
        _state.update { it.copy(error = "Forgot password clicked") }
    }
}
