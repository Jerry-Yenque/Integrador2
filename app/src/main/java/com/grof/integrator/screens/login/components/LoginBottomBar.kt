package com.grof.integrator.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginBottomBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No puedes iniciar sesión?",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = "SOPORTE AQUÍ",
            fontSize = 14.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginBottomBar()
}