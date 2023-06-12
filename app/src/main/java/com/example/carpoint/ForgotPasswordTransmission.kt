package com.example.carpoint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class ForgotPasswordTransmission : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ){
                displayLogo()
                createTextField(placeholderResId = R.string.enterYourEmail, leadingIcon = Icons.Default.Email)
                createButton(placeholderResId = R.string.submit)

            }
        }
    }
}

@Preview
@Composable
fun transmissionPreview(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        displayLogo()
        createTextField(placeholderResId = R.string.enterYourEmail, leadingIcon = Icons.Default.Email)
        createButton(placeholderResId = R.string.resetPassword)

    }
}