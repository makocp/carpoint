package com.example.carpoint.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carpoint.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Utils {
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
fun createButton(@StringRes placeholderResId: Int,scope : CoroutineScope ,onClick: () -> Unit) {

    MaterialTheme() {
        Button(
            onClick = {scope.launch {
                onClick
            }} ,
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
fun createTextField(@StringRes placeholderResId: Int, leadingIcon: ImageVector, value: String,onTextChanged: (String) -> Unit) {
    MaterialTheme {
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp),
            value = value,
            leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = "emailIcon") },
            onValueChange = { onTextChanged },
            placeholder = { Text(text = stringResource(placeholderResId)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createPasswordField(@StringRes placeholderResId: Int, leadingIcon: ImageVector) {
    MaterialTheme {
        val inputValue = remember { mutableStateOf(TextFieldValue()) }
        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.displayed)
        else
            painterResource(id = R.drawable.hidden)

        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            value = inputValue.value,
            leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = "emailIcon") },
            onValueChange = { inputValue.value = it
                            print(it)},
            placeholder = { Text(text = stringResource(placeholderResId)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                if (inputValue.value.text.isNotEmpty()) {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility Icon",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        )
    }
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
fun addClickableText(@StringRes text: Int, fontsize: Int, color: Color, onClick: (Int) -> Unit) {
    ClickableText(
        text = AnnotatedString(stringResource(text)),
        style = TextStyle(color = color, fontSize = fontsize.sp),
        modifier = Modifier
            .padding(start = 4.dp),
        onClick = onClick
    )
}
