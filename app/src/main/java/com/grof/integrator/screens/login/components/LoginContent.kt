package com.grof.integrator.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grof.integrator.R
import com.grof.integrator.ui.theme.Darkgray500
import com.grof.integrator.ui.theme.IntegratorTheme
import com.grof.integrator.ui.theme.Red500

@Composable
fun LoginContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        //.wrapContentHeight() Content Center Vertical
        //.background(Color.White),
    ) {
        BoxHeader()
        CardForm()
    }
}

@Composable
fun BoxHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(Red500)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(130.dp).width(300.dp),
                painter = painterResource(id = R.drawable.unfv),
                contentDescription = "unfv logo"
            )
            Text(
                text = "Asistencia UNFV"
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardForm() {
    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 170.dp)
            .background(color = Darkgray500) //In example = ,backgroundColor = DarkGray500  including ',' seccion 2 ep. 8
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
            OutlinedTextField(
                modifier = Modifier.padding(top = 25.dp),
                value = "",
                onValueChange = { },
                label = {
                    Text("Correo Institucional")
                },
                leadingIcon = { // trailingIcon to put it in right side, seccion 2 ep. 8
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email icon",
                        tint = Color.White
                    )
                }
            )
            OutlinedTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = "",
                onValueChange = { },
                label = {
                    Text("Contraseña")
                },
                leadingIcon = { // trailingIcon to put it in right side, seccion 2 ep. 8
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "clave icon",
                        tint = Color.White
                    )
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 45.dp),
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "arrow")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "INICIAR SESIÓN")
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    //IntegratorTheme() {
    //Greeting("Android") Part of Hello World Compose
    //    BodyContent()
    //}
    IntegratorTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //Greeting("Android") Part of Hello World Compose
            LoginContent()
        }
    }
}