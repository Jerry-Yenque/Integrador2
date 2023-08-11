package com.grof.integrator.presentation.navigation

// sealed class is like enum in java, to collect data
sealed class AppScreen(val route: String) { // Se recibe la ruta a abrir
    // Esta parte es asignarle al objeto Attendance una instancia de una clase anonima que hereda de AppScreen
    // Inicializando su dato miembro(parametro) con el valor/argumento "attendance"

    /*
    *   sealed class Father() {
    *   object Child1: Father()
    *   object Child2: Father()
    * }
    * */

    object Attendance: AppScreen("attendance")
    object About: AppScreen("about")

    // BELOW DEPRECATED
    object Login: AppScreen("login")
    object Signup: AppScreen("signup")
}
