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
    // Email
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    // Password
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    // Button
    var isEnabledLoginButton = false

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    val currentUser = authUseCases.getCurrentUser()
    init {
        if (currentUser != null) { // Sesion iniciada
            _loginFlow.value = Response.Success(currentUser)
        }
    }
    fun login() = viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val result = authUseCases.login(email.value, password.value)
        _loginFlow.value = result
    }
    fun enabledLoginButton() {
        isEnabledLoginButton = isEmailValid.value && isPasswordValid.value
    }

    fun validateEmail() {
        if (email.value.matches(Regex(".*@unfv\\.edu\\.pe$"))) { //i.e In example is Patterns.EMAIL_ADDRESS.matcher(email.value).matches(); instead i use regular expresion to match unfv domain.
            isEmailValid.value = true
            emailErrMsg.value = ""
        }
        else {
            isEmailValid.value = false
            emailErrMsg.value = "El email no corresponde con el dominio @unfv.edu.pe"
        }
        enabledLoginButton()
    }

    fun validatePassword() {
        if (password.value.length >= 6) { // Firebase pide al menos 6 caracteres
            isPasswordValid.value = true
            passwordErrMsg.value = ""
        }
        else {
            isPasswordValid.value = false
            passwordErrMsg.value = "Al menos 6 caracteres"
        }
        enabledLoginButton()
    }
}