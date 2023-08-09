package com.grof.integrator.presentation.screens.profile_update

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.grof.integrator.presentation.components.DefaultTopBar
import com.grof.integrator.presentation.screens.profile_update.components.ProfileEditContent
import com.grof.integrator.presentation.screens.profile_update.components.SaveImage
import com.grof.integrator.presentation.screens.profile_update.components.Update


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    user: String
) {
    Log.d("ProfileEditScreen", "Usuario: $user")
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Registrar Asistencia",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileEditContent(navController)
        },
        bottomBar = {}
    )
    SaveImage()
    Update()
}