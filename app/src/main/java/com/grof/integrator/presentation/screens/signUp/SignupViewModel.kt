package com.grof.integrator.presentation.screens.signUp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(): ViewModel() {
    // Email
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    // Password
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    // Confirm Password
    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isconfirmPassword: MutableState<Boolean> = mutableStateOf(false) // CamelCase trucho
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    // Button
    var isEnabledLoginButton = false


    fun enabledLoginButton() {
        isEnabledLoginButton =
            isEmailValid.value &&
                    isPasswordValid.value &&
                    isconfirmPassword.value
    }

    fun validateConfirmPassword() {
        if(password.value == confirmPassword.value) {
            isconfirmPassword.value = true
            confirmPasswordErrMsg.value = ""
        }
        else {
            isconfirmPassword.value = false
            confirmPasswordErrMsg.value = "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }
    // fun validateUsername()  this function is not implemented, seccion 3. ep 17. min 5:48

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