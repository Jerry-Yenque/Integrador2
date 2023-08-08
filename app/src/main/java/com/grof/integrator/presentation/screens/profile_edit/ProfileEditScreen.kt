package com.grof.integrator.presentation.screens.profile_edit

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.grof.integrator.presentation.components.DefaultTopBar
import com.grof.integrator.presentation.screens.profile_edit.components.ProfileEditContent


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    user: String
) {
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
}