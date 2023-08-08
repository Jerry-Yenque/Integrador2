package com.grof.integrator.presentation.screens.signUp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.grof.integrator.domain.model.Response
import com.grof.integrator.domain.model.User
import com.grof.integrator.domain.use_cases.auth.AuthUseCases
import com.grof.integrator.domain.use_cases.auth.SignUp
import com.grof.integrator.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases): ViewModel() {
    // State Form
    var state by mutableStateOf(SignupState())
        private set

    // Here Goes username or what you want
    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    // Email
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set

    // Password
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    // Confirm Password
    var isconfirmPassword by mutableStateOf(false) // CamelCase trucho
        private set
    var confirmPasswordErrMsg by mutableStateOf("")
        private set

    // Button
    var isEnabledLoginButton = false

    var signupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var user = User()

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    // SignupState no contiene username, por implementar
    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }
    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }
    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }
    fun onSignUp() {
        user.username = ""  // aquí se establece en string vacio para la bd
        user.email = state.email
        user.password = state.password
        signup(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }
    fun signup(user: User) = viewModelScope.launch {
        signupResponse = Response.Loading
        val result = authUseCases.signup(user)
        signupResponse = result
    }
    fun enabledLoginButton() {
        isEnabledLoginButton =
                    isEmailValid &&
                    isPasswordValid &&
                    isconfirmPassword     //por agregar isUsernameValid
    }

    fun validateConfirmPassword() {
        if(state.password == state.confirmPassword) {
            isconfirmPassword = true
            confirmPasswordErrMsg = ""
        }
        else {
            isconfirmPassword = false
            confirmPasswordErrMsg = "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }
    // fun validateUsername()  this function is not implemented, seccion 3. ep 17. min 5:48
    fun validateUsername() {
        if (state.username.length >= 6) { // Firebase pide al menos 6 caracteres
            isUsernameValid = true
            usernameErrMsg = ""
        }
        else {
            isUsernameValid = false
            usernameErrMsg = "Al menos 6 caracteres"
        }
        enabledLoginButton()
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