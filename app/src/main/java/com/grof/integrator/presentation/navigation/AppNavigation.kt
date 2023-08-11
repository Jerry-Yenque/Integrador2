package com.grof.integrator.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grof.integrator.presentation.screens.attendance.AttendanceScreen
import com.grof.integrator.presentation.screens.login.LoginScreen
import com.grof.integrator.presentation.screens.signUp.SignupScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Attendance.route
    ) {

        composable(route = AppScreen.Attendance.route) {
            AttendanceScreen(navController)
        }
//        composable(route = AppScreen.About.route) {
//            AboutScreen(navController)
//        }

        // BELOW DEPRECATED
        composable(route = AppScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreen.Signup.route) {
            SignupScreen(navController)
        }
    }
}