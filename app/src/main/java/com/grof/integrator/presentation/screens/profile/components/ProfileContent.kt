package com.grof.integrator.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.grof.integrator.R
import com.grof.integrator.presentation.components.DefaultButton
import com.grof.integrator.presentation.navigation.AppScreen
import com.grof.integrator.presentation.screens.profile.ProfileViewModel

@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                painter = painterResource(id = R.drawable.unfv3),
                contentDescription = "Banner unfv",
                contentScale = ContentScale.Crop, // Para escalar imagenes, en el ejemplo usado para el ancho, la foto era cuadrada
                alpha = 0.6f
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Bienvenido",
                    color = Color(0xFFFFA500),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(80.dp))
                Image(
                    modifier = Modifier.size(115.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "User picture"
                )
            }
        }
        Spacer(modifier = Modifier.height(55.dp))
        Text(
            text = "Nombre",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "Correo Institucional",
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(20.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Registrar Asistencia",
            errorMsg = "",
            color = Color.White,
            icon = Icons.Default.Person,
            onClick = {
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Cerrar sesión",
            errorMsg = "",
            icon = Icons.Default.Close,
            onClick = {
                   viewModel.logout()
                   navController.navigate(route = AppScreen.Login.route) {
                       popUpTo(AppScreen.Profile.route) { inclusive = true }
                   }
            }
        )
    }
}

