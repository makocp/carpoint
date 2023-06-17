package com.example.carpoint.Screens.LogIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carpoint.R
import com.example.carpoint.addClickableText
import com.example.carpoint.addDivider
import com.example.carpoint.addText
import com.example.carpoint.createButton
import com.example.carpoint.createTextField
import com.example.carpoint.displayLogo
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LogInScreen(navController: NavController, viewModel: LogInViewModel = hiltViewModel()) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val email by remember { mutableStateOf("") }
        val password by remember { mutableStateOf("") }

        displayLogo()
        createTextField(placeholderResId = R.string.email, leadingIcon = Icons.Default.Email)
        createTextField(placeholderResId = R.string.password, leadingIcon = Icons.Default.Lock)
        addClickableText(text = R.string.forgotPassword, fontsize = 15, color = Color(0xFF1e88c1),{ navController.navigate("resetpasswordTransmission")})
        createButton(placeholderResId = R.string.logIn, {viewModel.log()})

        addDivider(padding = 30)
        Row {
            addText(text = R.string.alreadyHaveAccount, fontsize = 15, Color.Gray)
            addClickableText(text = R.string.signUp, fontsize = 15, color = Color(0xFF1e88c1),{navController.navigate("signup")})
        }
    }
}

