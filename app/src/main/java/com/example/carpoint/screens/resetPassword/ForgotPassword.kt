package com.example.carpoint.screens.resetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.carpoint.R
import com.example.carpoint.utils.addText

@Composable
fun ResetPassword(navController: NavController) {
    val scope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        addText(text = R.string.enterNewPassword, fontsize = 25, color = Color.Black)
        // createPasswordField(placeholderResId = R.string.enterNewPassword, leadingIcon = Icons.Default.Edit)
        // createPasswordField(placeholderResId = R.string.confirmNewPassword, leadingIcon = Icons.Default.Check)
        // createButton(placeholderResId = R.string.resetPassword,scope,{    navController.popBackStack(navController.graph.startDestinationId, false) })
    }
}

