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
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.carpoint.utils.TextFieldComponent
import com.example.carpoint.utils.createPasswordField
import com.example.carpoint.utils.indicateProgressing
import kotlinx.coroutines.launch


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
        displayLogo()
        addText(text = R.string.logIn, fontsize = 30, color = Color.Black)
        createTextField(
            placeholderResId = R.string.email,
            leadingIcon = Icons.Default.Email,
            value = email,
            onTextChanged = { email = it })

        createPasswordField(
            placeholderResId = R.string.password,
            leadingIcon = Icons.Default.Lock,
            value = password,
            onValueChange = { password = it })

        addClickableText(
            text = R.string.forgotPassword,
            fontsize = 15,
            color = Color(0xFF1e88c1),
            { navController.navigate("resetpasswordTransmission") })

        createButton(R.string.logIn, { scope.launch { viewModel.loginUser(email, password) } })

        addDivider(padding = 30)

        Row {           
            addText(text = R.string.dontHaveAccount, fontsize = 15, Color.Gray)
            addClickableText(text = R.string.signUp, fontsize = 15, color = Color(0xFF1e88c1),{})

        if (state.value?.isLoading == true) {
       indicateProgressing()
        } else if (state.value?.isSuccess?.isNotEmpty() == true) {
            navController.navigate("dashboard")
        } else if (state.value?.isError?.isNotEmpty() == true) {
            Toast.makeText(context, state.value?.isError, Toast.LENGTH_SHORT).show()

        }
    }
}

