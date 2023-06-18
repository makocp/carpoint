package com.example.carpoint.Screens.ResetPassword
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.carpoint.R
import com.example.carpoint.utils.createButton
import com.example.carpoint.utils.createTextField
import com.example.carpoint.utils.displayLogo

@Composable
fun ResetPasswordTransmission(navController : NavController){
    var scope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        displayLogo()
        createTextField(placeholderResId = R.string.enterYourEmail, leadingIcon = Icons.Default.Email,"",{})
        createButton(placeholderResId = R.string.submit, scope,{navController.navigate("resetpassword")})
    }
}
