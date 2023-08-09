package com.grof.integrator.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.grof.integrator.domain.model.Response
import com.grof.integrator.presentation.components.ProgressBar
import com.grof.integrator.presentation.navigation.AppScreen
import com.grof.integrator.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController,viewModel: LoginViewModel = hiltViewModel()) {
    when(val loginResponse = viewModel.loginResponse) {
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(route = AppScreen.Profile.route) {
                    popUpTo(AppScreen.Login.route) { inclusive = true }
                }
            }
            //Toast.makeText(LocalContext.current, "Usuario logeado", Toast.LENGTH_LONG).show() Para control!
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        // Agregado para evitar error, no en el ejemplo
        else -> {}
    }
}