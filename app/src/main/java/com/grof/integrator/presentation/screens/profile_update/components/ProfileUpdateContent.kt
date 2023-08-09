package com.grof.integrator.presentation.screens.profile_update.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.grof.integrator.R
import com.grof.integrator.presentation.components.DefaultButton
import com.grof.integrator.presentation.components.DefaultTextField
import com.grof.integrator.presentation.components.DialogCapturePicture
import com.grof.integrator.presentation.screens.profile_update.ProfileUpdateViewModel
import com.grof.integrator.presentation.ui.theme.Darkgray500
import com.grof.integrator.presentation.ui.theme.Red500

@Composable
fun ProfileEditContent(navController: NavHostController, viewModel: ProfileUpdateViewModel = hiltViewModel()) {
    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()
    var dialogState = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )
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
                Spacer(modifier = Modifier.height(80.dp))
                if(viewModel.imageUri != "") {
                    AsyncImage(
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .clip(CircleShape)
                            .clickable {
                                       dialogState.value = true
                            },
                        model = viewModel.imageUri,
                        contentDescription = "Selected image",
                        contentScale = ContentScale.Crop
                    )
                }
                else {
                    Image(
                        modifier = Modifier
                            .size(115.dp)
                            .clickable {
                                dialogState.value = true
                            },
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "User picture"
                    )
                }
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
                    text = "ASISTENCIA",
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
                    value = state.username, // Mostramos email
                    onValueChange = {viewModel.onUsernameInput(it)},
                    label = "Nombre",
                    icon = Icons.Default.Person, //.Person instead .Email
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = {viewModel.validateUsername()}
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 40.dp),
                    text = "Actualizar datos",
                    onClick = { viewModel.onUpdate() },
                    errorMsg = "", // Added to avoid errMsg has no value
                )
            }
        }
    }
}