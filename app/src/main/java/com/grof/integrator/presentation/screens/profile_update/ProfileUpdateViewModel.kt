package com.grof.integrator.presentation.screens.profile_update

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grof.integrator.domain.model.Response
import com.grof.integrator.domain.model.User
import com.grof.integrator.domain.use_cases.users.UsersUseCases
import com.grof.integrator.presentation.utils.ComposeFileProvider
import com.grof.integrator.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
    ): ViewModel() {
    // State Form
    var state by mutableStateOf(ProfileUpdateState())
        private set
    var usernameErrMsg by mutableStateOf("")
        private set
    val userString = savedStateHandle.get<String>("user")
    val user = User.fromJson(userString!!)

    // Response
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    // Image
    var imageUri by mutableStateOf("")

    val resultingActivityHandler = ResultingActivityHandler()

    init {
        // Set arguments
        state = state.copy(username = user.username, email = user.email) // email added to see his content ep32 12:18 <- less than this
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("images/*")
        if(result != null) {
            imageUri = result.toString()
        }
    }
    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if(result != null) {
            imageUri = ComposeFileProvider.getPathFromBitmap(context, result)
        }
    }

    fun onUpdate() {
        var myUser = User(
            id = user.id,
            username = state.username,
            image = ""
        )
        update(myUser)
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user)
        updateResponse = result
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