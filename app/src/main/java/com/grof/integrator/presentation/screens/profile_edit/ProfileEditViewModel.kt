package com.grof.integrator.presentation.screens.profile_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.grof.integrator.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel() {
    // State Form
    var state by mutableStateOf(ProfileEditState())
        private set
    var usernameErrMsg by mutableStateOf("")
        private set
    val userString = savedStateHandle.get<String>("user")
    val user = User.fromJson(userString!!)

    init {
        state = state.copy(username = user.email, email = user.email) // email added to see his content ep32 12:18 <- less than this
    }
    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun validateUsername() {
        if (state.username.length >= 6) { // Firebase pide al menos 6 caracteres
            usernameErrMsg = ""
        }
        else {
            usernameErrMsg = "Al menos 6 caracteres"
        }
    }
}