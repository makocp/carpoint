package com.example.carpoint.screens.resetPassword
/**
ForgotPasswordScreen.kt
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.carpoint.R
import com.example.carpoint.utils.CreateButton
import com.example.carpoint.utils.CreateTextField
import com.example.carpoint.utils.DisplayLogo
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DisplayLogo()
        CreateTextField(
            placeholderResId = R.string.enterYourEmail,
            leadingIcon = Icons.Default.Email,
            email,
            { email = it })
        CreateButton(placeholderResId = R.string.submit
        ) {
            if (email.isNotEmpty()) {
                scope.launch {
                    viewModel.sendResetEmail(email)
                }
            }
            navController.navigate("login")
        }
    }
}
