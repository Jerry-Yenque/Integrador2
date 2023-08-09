package com.grof.integrator.presentation.screens.signUp.components

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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.grof.integrator.R
import com.grof.integrator.domain.model.Response
import com.grof.integrator.presentation.components.DefaultButton
import com.grof.integrator.presentation.components.DefaultTextField
import com.grof.integrator.presentation.navigation.AppScreen
import com.grof.integrator.presentation.screens.signUp.SignupViewModel
import com.grof.integrator.presentation.ui.theme.Darkgray500
import com.grof.integrator.presentation.ui.theme.IntegratorTheme
import com.grof.integrator.presentation.ui.theme.Red500

@Composable
//fun SignupContent(paddingValues: PaddingValues) {
fun SignupContent(navController: NavHostController, viewModel: SignupViewModel = hiltViewModel()) {
    val signupFLow = viewModel.signupFlow.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
        //.padding(paddingValues),
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
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    modifier = Modifier
                        .height(150.dp)
                        .width(300.dp),
                    painter = painterResource(id = R.drawable.unfv),
                    contentDescription = "unfv logo"
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
                    text = "REGISTRO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Ingresa tus datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp), // 25.dp in example
                    value = viewModel.email.value,
                    onValueChange = {viewModel.email.value = it},
                    label = "Correo Institucional",
                    icon = Icons.Default.Person, //.Person instead .Email
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg.value,
                    validateField = {viewModel.validateEmail()}
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = viewModel.password.value,
                    onValueChange = {viewModel.password.value = it},
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg.value,
                    validateField = {viewModel.validatePassword()}
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = viewModel.confirmPassword.value,
                    onValueChange = {viewModel.confirmPassword.value = it},
                    label = "Confirmar Contraseña",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    errorMsg = viewModel.confirmPasswordErrMsg.value,
                    validateField = {viewModel.validateConfirmPassword()}
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    text = "Registrarse",
                    onClick = { viewModel.onSignUp() },
                    errorMsg = "", // Added to avoid errMsg has no value
                    enabled = viewModel.isEnabledLoginButton
                )
            }
        }
    }
    signupFLow.value.let {
        when(it) {
            Response.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    viewModel.createUser()
                    navController.popBackStack(AppScreen.Login.route, inclusive = true)
                    navController.navigate(route = AppScreen.Profile.route)
                }
            }
            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error unknow", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
}

// Los Preview NO son compatibles con Injection de dependencias, hay manera, no mostrada en el ejemplo
// Se borra en el ejemplo, se deja comentado.
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewSignupContent() {
//    IntegratorTheme(darkTheme = true) {
//        // A surface container using the 'background' color from the theme
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            //SignupContent(paddingValues = PaddingValues(top = 30.dp))
//            SignupContent()
//        }
//    }
//}