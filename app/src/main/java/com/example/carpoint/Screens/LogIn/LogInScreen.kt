package com.example.carpoint.Screens.LogIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.carpoint.R
import com.example.carpoint.utils.addClickableText
import com.example.carpoint.utils.addDivider
import com.example.carpoint.utils.addText
import com.example.carpoint.utils.createButton
import com.example.carpoint.utils.createTextField
import com.example.carpoint.utils.displayLogo
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carpoint.Authentication.IAuthentication


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
        val scope = rememberCoroutineScope()

        displayLogo()
        createTextField(placeholderResId = R.string.email, leadingIcon = Icons.Default.Email,"",{})
        createTextField(placeholderResId = R.string.password, leadingIcon = Icons.Default.Lock,"",{})
        addClickableText(text = R.string.forgotPassword, fontsize = 15, color = Color(0xFF1e88c1),{ navController.navigate("resetpasswordTransmission")})
        createButton(placeholderResId = R.string.logIn, scope,{viewModel.loginUser("mohammed.alamer@edu.fh-joanneum.at", "!Admin321")})

        addDivider(padding = 30)
        Row {
            addText(text = R.string.alreadyHaveAccount, fontsize = 15, Color.Gray)
            addClickableText(text = R.string.signUp, fontsize = 15, color = Color(0xFF1e88c1),{navController.navigate("signup")})
        }
    }
}
