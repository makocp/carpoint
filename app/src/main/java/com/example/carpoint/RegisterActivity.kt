package com.example.carpoint

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {

        }
    }
}

@Composable
fun displayLogo() {
    MaterialTheme {
        Image(
            modifier = Modifier
                .size(300.dp),
            painter = painterResource(R.mipmap.ic_launcher_foreground),
            contentDescription = "AppLogo"
        )
    }

}

@Composable
fun createButton(@StringRes placeholderResId: Int) {

    MaterialTheme() {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color(0xFF1e88c1)),
            modifier = Modifier
                .padding( 50.dp),

            ) {
        Text(text = stringResource(id = placeholderResId))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createTextField(@StringRes placeholderResId: Int) {
    MaterialTheme {
        val inputValue = remember {
            mutableStateOf(TextFieldValue())
        }
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
            placeholder = { Text(text = stringResource(placeholderResId)) }
        )
    }
}

@Preview
@Composable
fun registerActivityPreview() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        displayLogo()
        createTextField(R.string.Username)
        createTextField(R.string.email)
        createTextField(R.string.password)
        createTextField(R.string.confirmPassword)
        createButton(placeholderResId = R.string.createAccount)
    }
}