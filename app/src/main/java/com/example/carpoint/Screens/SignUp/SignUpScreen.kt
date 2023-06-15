package com.example.carpoint.Screens.SignUp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.carpoint.R
import com.example.carpoint.addClickableText
import com.example.carpoint.addDivider
import com.example.carpoint.addText
import com.example.carpoint.createButton
import com.example.carpoint.createPasswordField
import com.example.carpoint.createTextField
import com.example.carpoint.displayLogo

@Composable
fun SignUpScreen(navController : NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        createTextField(R.string.Username, Icons.Default.Create)
        createTextField(R.string.email, Icons.Default.Email)
        createPasswordField(R.string.password, Icons.Default.Lock)
        createPasswordField(R.string.confirmPassword, Icons.Default.Lock)
        createButton(placeholderResId = R.string.createAccount,{navController.navigate("dashboard")})
        addDivider(30)
        Row {
            addText(text = R.string.alreadyHaveAccount, fontsize = 15, Color.Gray)
            addClickableText(text = R.string.logIn, fontsize = 15, color = Color(0xFF1e88c1),{ navController.popBackStack()})
        }
    }
}

fun navigate(navController: NavController){
    navController.navigate("login")
    navController.popBackStack()
}