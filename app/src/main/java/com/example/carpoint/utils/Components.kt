package com.example.carpoint.utils
/**
Components.kt
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carpoint.R
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.MutableState

@Composable
fun AddDivider(padding: Int) {
    Divider(
        color = Color.Black,
        modifier = Modifier
            .padding(
                start = padding.dp,
                end = padding.dp,
                bottom = padding.dp
            )
            .fillMaxWidth()
    )
}


@Composable
fun DisplayLogo() {
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
fun CreateButton(@StringRes placeholderResId: Int, onClick: () -> Unit) {

    MaterialTheme() {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(Color(0xFF1e88c1)),
            modifier = Modifier
                .padding(10.dp, 0.dp, 0.dp, 10.dp),
        ) {
            Text(text = stringResource(id = placeholderResId))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTextField(
    @StringRes placeholderResId: Int,
    leadingIcon: ImageVector,
    value: String,
    onTextChanged: (String) -> Unit
) {
    val gradientBorder = BorderStroke(
        width = 2.dp,
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFF1e88c1), Color(0xFF000000)),
        )
    )

    MaterialTheme {
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .height(TextFieldDefaults.MinHeight)
                .width(280.dp)
                .border(gradientBorder),
            value = value,
            leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = "Icon") },
            onValueChange = onTextChanged,
            placeholder = { Text(text = stringResource(placeholderResId)) },
            singleLine = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInputField(
    @StringRes placeholderResId: Int,
    value: String,
    onTextChanged: (String) -> Unit
) {
    val gradientBorder = BorderStroke(
        width = 2.dp,
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFF1e88c1), Color(0xFF000000)),
        )
    )

    MaterialTheme {
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .height(TextFieldDefaults.MinHeight)
                .width(280.dp)
                .border(gradientBorder),
            value = value,
            onValueChange = onTextChanged,
            placeholder = { Text(text = stringResource(placeholderResId)) },
            singleLine = false
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordField(
    @StringRes placeholderResId: Int,
    leadingIcon: ImageVector,
    value: String,
    onValueChange: (String) -> Unit
) {
    MaterialTheme {
        val gradientBorder = BorderStroke(
            width = 2.dp,
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xFF1e88c1), Color(0xFF000000)),
            )
        )


        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.displayed)
        else
            painterResource(id = R.drawable.hidden)

        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .width(280.dp)
                .height(TextFieldDefaults.MinHeight)
                .border(gradientBorder),

            value = value,
            leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = "emailIcon") },
            onValueChange = onValueChange,
            placeholder = { Text(text = stringResource(placeholderResId)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                if (value.isNotEmpty()) {
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
fun AddText(@StringRes text: Int, fontsize: Int, color: Color) {
    Text(
        text = stringResource(id = text),
        modifier = Modifier,
        color = color,
        style = TextStyle(fontSize = fontsize.sp)
    )
}

@Composable
fun AddClickableText(@StringRes text: Int, fontsize: Int, color: Color, onClick: (Int) -> Unit) {
    ClickableText(
        text = AnnotatedString(stringResource(text)),
        style = TextStyle(color = color, fontSize = fontsize.sp),
        modifier = Modifier
            .padding(start = 4.dp),
        onClick = onClick
    )
}

@Composable
fun IndicateProgressing() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(12.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(12.dp)
                .align(Alignment.Center)
                .progressSemantics()
        )
    }
}


@Composable
fun NoteCard(date: String, note: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, Color(0xFF1e88c1)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = date,
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleSmall
                )
                IconButton(
                    onClick = onClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Composable
fun SheetContent() {
    // Your sheet content layout goes here
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    ) {
        // Add your content inside the Box
        Text(
            text = "This is the sheet content",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun AddFloatingActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        shape = RoundedCornerShape(50.dp),
        onClick = onClick,
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}

@Composable
fun NextStepsComponent(
    modifier: Modifier = Modifier,
    nextSteps: List<String>
) {
    Column(modifier = modifier) {
        Text(
            text = "Next Steps:",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        nextSteps.forEach { step ->
            Text(
                text = "\u2022 $step",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}


@Composable
fun alertDialog(
    showDialog: MutableState<Boolean>,
    @StringRes placeholderResId: Int,
    onClick: () -> Unit
) {
    if (showDialog.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                text = { Text(text = stringResource(placeholderResId)) },
                confirmButton = {
                    CreateButton(placeholderResId = R.string.yes) {
                        onClick()
                        showDialog.value = false
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog.value = false
                        }
                    ) {
                        Text(stringResource(R.string.no))
                    }
                }
            )
        }
    }
}