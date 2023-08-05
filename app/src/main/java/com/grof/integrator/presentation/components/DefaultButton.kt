package com.grof.integrator.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.grof.integrator.presentation.ui.theme.Red500

@Composable
fun DefaultButton(
    text: String,
    onClick: () -> Unit,
    color: Color = Red500,
    icon: ImageVector =Icons.Default.ArrowForward
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 45.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = color) // In example has attribute backgroundColor, now it doesn't seem have it, instead i tried containerColor (seccion 2 ep 10.)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "defaultbuttoncomponent")
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text)
    }
}