package com.example.carpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.carpoint.ui.theme.CarPointTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                displayLogo()
                createTextField(R.string.Username, Icons.Default.Create)
                createTextField(R.string.email, Icons.Default.Email)
                createPasswordField(R.string.password, Icons.Default.Lock)
                createPasswordField(R.string.confirmPassword, Icons.Default.Lock)
                createButton(placeholderResId = R.string.createAccount)
                addDivider(30)
                Row {
                    addText(text = R.string.alreadyHaveAccount, fontsize = 15, Color.Gray)
                    addClickableText(text = R.string.logIn, fontsize = 15, color = Color(0xFF1e88c1))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarPointTheme {
        Greeting("Marin")
    }
}