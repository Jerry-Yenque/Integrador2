package com.grof.integrator.presentation.screens.attendance.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(): ViewModel() {
//        var codigoEstudiante by remember { // esta sintaxis solo para funciones composable
//            mutableStateOf("")
//        }
//        var codigoCurso by remember {
//            mutableStateOf("")
//        }

    // CODIGO ESTUDIANTE
    var estudiante: MutableState<String> = mutableStateOf("")
    var isEstudianteValid: MutableState<Boolean> = mutableStateOf(false)
    var estudianteErrMsg: MutableState<String> = mutableStateOf("")

    // CODIGO CURSO
    var curso: MutableState<String> = mutableStateOf("")
    var isCursoValid: MutableState<Boolean> = mutableStateOf(false)
    var cursoErrMsg: MutableState<String> = mutableStateOf("")

    // BUTTON
    var isEnabledRegistrarButton = false
    fun enabledRegistrarButton() {
        isEnabledRegistrarButton = isEstudianteValid.value && isCursoValid.value
    }

    fun validateEstudiante() {
        // Aquí debe ir las validaciones (llamada a querys?)
        // El código es de 10 dígitos
        if (estudiante.value.length == 10) {
            isEstudianteValid.value = true
            estudianteErrMsg.value = ""
        }
        else {
            isEstudianteValid.value = false
            estudianteErrMsg.value = "El código de alumno tiene 10 dígitos"
        }
        enabledRegistrarButton()
    }

    fun validateCurso() {
        // 4 digitos por ahora
        if (curso.value.length == 4) {
            isCursoValid.value = true
            cursoErrMsg.value = ""
        }
        else {
            isCursoValid.value = false
            cursoErrMsg.value = "El código del curso tiene 4 dígitos"
        }
        enabledRegistrarButton()
    }
}