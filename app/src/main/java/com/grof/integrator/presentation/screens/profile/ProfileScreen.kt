package com.grof.integrator.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.grof.integrator.presentation.components.DefaultButton
import com.grof.integrator.presentation.navigation.AppScreen
import com.grof.integrator.presentation.screens.profile.components.ProfileContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {},
        content = {
                  ProfileContent(navController)
        },
        bottomBar = {}
    )
}