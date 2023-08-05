package com.grof.integrator.presentation.screens.signUp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.grof.integrator.presentation.components.DefaultTopBar
import com.grof.integrator.presentation.screens.signUp.components.SignupContent
import com.grof.integrator.presentation.ui.theme.IntegratorTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
                DefaultTopBar(
                     title = "Nuevo usuario",
                     upAvailable = true,
                     navController = navController
                 )
        },
        content = {
                  //SignupContent(paddingValues = it)
                  SignupContent()
        },
        bottomBar = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignupScreen() {
    IntegratorTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //Greeting("Android") Part of Hello World Compose
            SignupScreen(rememberNavController())
        }
    }
}