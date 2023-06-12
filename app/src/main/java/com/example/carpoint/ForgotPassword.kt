package com.example.carpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class ForgotPassword : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent(){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ){
                addText(text = R.string.enterNewPassword, fontsize = 25, color = Color.Black)
                createPasswordField(placeholderResId = R.string.enterNewPassword, leadingIcon = Icons.Default.Edit)
                createPasswordField(placeholderResId = R.string.confirmNewPassword, leadingIcon = Icons.Default.Check)
                createButton(placeholderResId = R.string.resetPassword)
            }
        }
    }
}

@Preview
@Composable
fun ForgotPasswordPreview(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        addText(text = R.string.enterNewPassword, fontsize = 25, color = Color.Black)
        createPasswordField(placeholderResId = R.string.enterNewPassword, leadingIcon = Icons.Default.Edit)
        createPasswordField(placeholderResId = R.string.confirmNewPassword, leadingIcon = Icons.Default.Check)
        createButton(placeholderResId = R.string.resetPassword)
    }
}