package com.grof.integrator.presentation.screens.attendance.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grof.integrator.presentation.ui.theme.Red500
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.grof.integrator.R
import com.grof.integrator.presentation.components.DefaultButton
import com.grof.integrator.presentation.components.DefaultTextField
import com.grof.integrator.presentation.ui.theme.Darkgray500
import com.grof.integrator.presentation.ui.theme.IntegratorTheme

@Composable
fun AttendanceContent(viewModel: AttendanceViewModel = hiltViewModel()) {
    //val state = viewModel.state

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
            Text(
                modifier = Modifier.align(Alignment.Center),
                //textAlign = TextAlign.Center,
                text = "BIENVENIDO",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
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
//                Text(
//                    text = "BIENVENIDO",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
            }
        }

        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 145.dp),
            colors = CardDefaults.cardColors(containerColor = Darkgray500)  //SOLVED: .background(color = DarkGray500) //In example = ,backgroundColor = DarkGray500  including ',' seccion 2 ep. 8
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 20.dp, bottom = 0.dp, start = 0.dp, end = 0.dp), //without top = applies to all sides
                    text = "ASISTENCIA",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Ingresa tus datos para registrar asistencia",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(35.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable { Log.i("Dog", "Click Click") }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp),
                            painter = painterResource(id = R.drawable.selfie),
                            contentDescription = "selfie icon"
                        )
                        Text(
                            text = "Agrega tu Selfie",
                            fontSize = 15.sp,
                            color = Color.Gray
                        )
                    }

                }
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = viewModel.estudiante.value,//codigoEstudiante, // state.email
                    onValueChange = { viewModel.estudiante.value = it }, //viewModel.onEmailInput(it) codigoEstudiante = it
                    label = "Código de Estudiante",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.NumberPassword,
                    errorMsg = viewModel.estudianteErrMsg.value,
                    validateField = {
                        viewModel.validateEstudiante()
                    }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.curso.value, //state.password codigoCurso
                    onValueChange = { viewModel.curso.value = it }, //viewModel.onPasswordInput(it) codigoCurso = it
                    label = "Código de Curso",
                    icon = Icons.Default.Info,
                    errorMsg = viewModel.cursoErrMsg.value,
                    keyboardType = KeyboardType.NumberPassword,
                    validateField = {
                        viewModel.validateCurso()
                    }
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    text = "REGISTRAR ASISTENCIA",
                    icon = Icons.Default.Send,
                    errorMsg = "", // This line is not in the example, was added to avoid errorMsg has not value
                    onClick = {
                        //viewModel.login()
                        Log.d("LoginContent", "Estudiante: ${viewModel.curso.value}")
                        Log.d("LoginContent", "Curso: ${viewModel.estudiante.value}")
                    },
                    enabled = viewModel.isEnabledRegistrarButton
                )
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AttendanceContentPreview() {
//    IntegratorTheme(darkTheme = true) {
//        // A surface container using the 'background' color from the theme
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            AttendanceContent()
//        }
//    }
//}