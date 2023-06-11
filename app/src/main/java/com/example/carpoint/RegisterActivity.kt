package com.example.carpoint

import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Telephony.Mms.Addr
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                .padding(50.dp),

            ) {
            Text(text = stringResource(id = placeholderResId))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createTextField(@StringRes placeholderResId: Int, leadingIcon: ImageVector) {
    MaterialTheme {
        val inputValue = remember {
            mutableStateOf(TextFieldValue())
        }
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = inputValue.value,
            leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = "emailIcon") },
            onValueChange = { inputValue.value = it },
            placeholder = { Text(text = stringResource(placeholderResId)) }
        )
    }
}

@Composable
fun addDivider(padding: Int) {
    Divider(
        color = Color.Black,
        modifier = Modifier
            .padding(
                start = padding.dp,
                end = padding.dp,
                bottom = padding.dp
            ) // Adjust the padding as needed
            .fillMaxWidth() // Take the maximum available width
    )
}

@Composable
fun addText(@StringRes text: Int, fontsize: Int, color: Color) {
    Text(
        text = stringResource(id = text),
        modifier = Modifier,
        color = color,
        style = TextStyle(fontSize = fontsize.sp)
    )
}

@Composable
fun addClickableText(@StringRes text: Int, fontsize: Int, color: Color) {
    ClickableText(
        text = AnnotatedString(stringResource(text)),
        style = TextStyle(color = color, fontSize = fontsize.sp),
        modifier = Modifier
            .padding(start = 4.dp),
        onClick = { /*TODO*/ }
    )
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
        createTextField(R.string.Username, Icons.Default.Create)
        createTextField(R.string.email, Icons.Default.Email)
        createTextField(R.string.password, Icons.Default.Lock)
        createTextField(R.string.confirmPassword, Icons.Default.Lock)
        createButton(placeholderResId = R.string.createAccount)
        addDivider(30)
        Row() {
            addText(text = R.string.alreadyHaveAccount, fontsize = 15, Color.Gray)
            addClickableText(text = R.string.signIn, fontsize = 15, color = Color(0xFF1e88c1))
        }
    }
}