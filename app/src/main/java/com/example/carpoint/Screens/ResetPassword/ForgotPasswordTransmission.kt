package com.example.carpoint.Screens.ResetPassword
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.carpoint.R
import com.example.carpoint.createButton
import com.example.carpoint.createTextField
import com.example.carpoint.displayLogo

@Composable
fun ResetPasswordTransmission(navController : NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        displayLogo()
        createTextField(placeholderResId = R.string.enterYourEmail, leadingIcon = Icons.Default.Email)
        createButton(placeholderResId = R.string.submit, {navController.navigate("resetpassword")})
    }
}
