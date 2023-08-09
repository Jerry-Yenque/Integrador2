package com.grof.integrator.presentation.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.grof.integrator.domain.model.Response
import com.grof.integrator.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {
    // STATE FORM
    var state by mutableStateOf(LoginState())
        private set

    // Email Validations
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set

    // Password Validations
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    // Enable Button
    var isEnabledLoginButton = false

    // Login Response
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    val currentUser = authUseCases.getCurrentUser()
    init {
        if (currentUser != null) { // Sesion iniciada
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }
    fun enabledLoginButton() {
        isEnabledLoginButton = isEmailValid && isPasswordValid
    }

    fun validateEmail() {
        if (state.email.matches(Regex(".*@unfv\\.edu\\.pe$"))) { //i.e In example is Patterns.EMAIL_ADDRESS.matcher(email.value).matches(); instead i use regular expresion to match unfv domain.
            isEmailValid = true
            emailErrMsg = ""
        }
        else {
            isEmailValid = false
            emailErrMsg = "El email no corresponde con el dominio @unfv.edu.pe"
        }
        enabledLoginButton()
    }

    fun validatePassword() {
        if (state.password.length >= 6) { // Firebase pide al menos 6 caracteres
            isPasswordValid = true
            passwordErrMsg = ""
        }
        else {
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }
        enabledLoginButton()
    }
}