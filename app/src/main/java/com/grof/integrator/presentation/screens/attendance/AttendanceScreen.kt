package com.grof.integrator.presentation.screens.attendance

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.grof.integrator.presentation.screens.attendance.components.AttendanceBottomBar
import com.grof.integrator.presentation.screens.attendance.components.AttendanceContent
import com.grof.integrator.presentation.screens.attendance.components.AttendanceViewModel
import com.grof.integrator.presentation.ui.theme.IntegratorTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(navController: NavHostController) {

    Scaffold(
        topBar = {},
        content = {
            AttendanceContent()
        },
        bottomBar = {
            AttendanceBottomBar(navController)
        }
    )
    // Manejar el estado de peticion
    //Login(navController = navController)
}


// ViewModel y Preview no son compatibles del to do
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AttendanceScreenPreview() {
//    IntegratorTheme(darkTheme = true) {
//        // A surface container using the 'background' color from the theme
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            AttendanceScreen(rememberNavController()) // rememberNavController para suplir el parametro y poder mostrar el preview
//        }
//    }
//}