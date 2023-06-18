package com.example.carpoint.Screens.LogIn

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
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
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navController: NavController, viewModel: LogInViewModel = hiltViewModel()) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        val state = viewModel.logInState.collectAsState(initial = null)
        val context = LocalContext.current
        var passwordVisibility by remember { mutableStateOf(false) }
        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.displayed)
        else
            painterResource(id = R.drawable.hidden)


        displayLogo()
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp),
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = stringResource(R.string.email)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "emailIcon"
                )
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp),
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = stringResource(R.string.password)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "emailIcon"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                if (password.isNotEmpty()) {
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
        addClickableText(
            text = R.string.forgotPassword,
            fontsize = 15,
            color = Color(0xFF1e88c1),
            { navController.navigate("resetpasswordTransmission") })
        Button(
            colors = ButtonDefaults.buttonColors(Color(0xFF1e88c1)),
            modifier = Modifier
                .padding(50.dp), onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)

                }
            })
        {
            Text(text = stringResource(id = R.string.logIn))

        }
        addDivider(padding = 30)
        Row {
            addText(text = R.string.alreadyHaveAccount, fontsize = 15, Color.Gray)
            addClickableText(
                text = R.string.signUp,
                fontsize = 15,
                color = Color(0xFF1e88c1),
                { navController.navigate("signup") })
        }

        if (state.value?.isLoading == true) {
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
        } else if (state.value?.isSuccess?.isNotEmpty() == true) {
            navController.navigate("dashboard")
        } else if (state.value?.isError?.isNotEmpty() == true) {
            Toast.makeText(context,state.value?.isError, Toast.LENGTH_SHORT).show()
        }
    }
}

