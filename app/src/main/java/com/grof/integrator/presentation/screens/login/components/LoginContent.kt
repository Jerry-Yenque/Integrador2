package com.grof.integrator.presentation.screens.login.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.grof.integrator.R
import com.grof.integrator.domain.model.Response
import com.grof.integrator.presentation.components.DefaultButton
import com.grof.integrator.presentation.components.DefaultTextField
import com.grof.integrator.presentation.navigation.AppScreen
import com.grof.integrator.presentation.screens.login.LoginViewModel
import com.grof.integrator.presentation.ui.theme.Darkgray500
import com.grof.integrator.presentation.ui.theme.Red500

@Composable
fun LoginContent(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    val loginFlow = viewModel.loginFlow.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        //.wrapContentHeight() Content Center Vertical
        //.background(Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Red500)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .height(150.dp)
                        .width(300.dp),
                    painter = painterResource(id = R.drawable.unfv),
                    contentDescription = "unfv logo"
                )
                Text(
                    text = "Asistencia UNFV"
                )
            }
        }

        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 200.dp),
            colors = CardDefaults.cardColors(containerColor = Darkgray500)  //SOLVED: .background(color = DarkGray500) //In example = ,backgroundColor = DarkGray500  including ',' seccion 2 ep. 8
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 40.dp, bottom = 0.dp, start = 0.dp, end = 0.dp), //without top = applies to all sides
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Inicia sesión para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = viewModel.email.value,
                    onValueChange = {viewModel.email.value = it},
                    label = "Correo Institucional",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg.value,
                    validateField = {
                        viewModel.validateEmail()
                    }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.password.value,
                    onValueChange = {viewModel.password.value = it},
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg.value,
                    validateField = {
                        viewModel.validatePassword()
                    }
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    text = "INICIAR SESIÓN",
                    errorMsg = "", // This line is not in the example, was added to avoid errorMsg has not value
                    onClick = {
                        viewModel.login()
                    },
                    enabled = viewModel.isEnabledLoginButton
                )
            }
        }
    }
    loginFlow.value.let {
        when(it) {
            Response.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(route = AppScreen.Profile.route)
                }
                //Toast.makeText(LocalContext.current, "Usuario logeado", Toast.LENGTH_LONG).show() Para control!
            }
            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
            // Agregado para evitar error, no en el ejemplo
            else -> {
                Toast.makeText(LocalContext.current, "Test Mode", Toast.LENGTH_SHORT).show()
            }
        }
    }
}