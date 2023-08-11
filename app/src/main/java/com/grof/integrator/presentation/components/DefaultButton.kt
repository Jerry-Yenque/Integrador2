package com.grof.integrator.presentation.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.sp
import com.grof.integrator.presentation.ui.theme.Red500
import com.grof.integrator.presentation.ui.theme.Red700

@Composable
fun DefaultButton(
    modifier: Modifier,
    text: String,
    errorMsg: String,
    onClick: () -> Unit,
    color: Color = Red500,
    icon: ImageVector =Icons.Default.ArrowForward,
    enabled: Boolean = true
) {
    Column() {
        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(containerColor = color), // In example has attribute backgroundColor, now it doesn't seem have it, instead i tried containerColor (seccion 2 ep 10.)
            enabled = enabled
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "defaultbuttoncomponent")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text)
        }
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = errorMsg,
            fontSize = 11.sp,
            color = Red700
        )
    }
}