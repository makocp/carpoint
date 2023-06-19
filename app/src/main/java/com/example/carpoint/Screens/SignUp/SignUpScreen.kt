package com.example.carpoint.Screens.SignUp

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.carpoint.R
import com.example.carpoint.utils.addClickableText
import com.example.carpoint.utils.addDivider
import com.example.carpoint.utils.addText
import com.example.carpoint.utils.createButton
import com.example.carpoint.utils.createPasswordField
import com.example.carpoint.utils.createTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {

    val scope = rememberCoroutineScope()
    val state = viewModel.signUpState.collectAsState(initial = null)
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        addText(text = R.string.createAccount, fontsize = 30, color = Color.Black)
        createTextField(
            placeholderResId = R.string.email,
            leadingIcon = Icons.Default.Email,
            value = email,
            onTextChanged = { email = it })
        createTextField(
            placeholderResId = R.string.Username,
            leadingIcon = Icons.Default.Person,
            value = username,
            onTextChanged = { username = it })
        createPasswordField(
            placeholderResId = R.string.password,
            leadingIcon = Icons.Default.Lock,
            value = password,
            onValueChange = { password = it })
        createPasswordField(
            placeholderResId = R.string.confirmPassword,
            leadingIcon = Icons.Default.Lock,
            value = confirmPassword,
            onValueChange = { confirmPassword = it })
        createButton(placeholderResId = R.string.createAccount, {
            if (password.equals(confirmPassword)) {
                scope.launch {
                    viewModel.createUser(email, password)
                }
            }
        })
        addDivider(padding = 30)
        Row {
            addText(text = R.string.alreadyHaveAccount, fontsize = 15, Color.Gray)
            addClickableText(
                text = R.string.logIn,
                fontsize = 15,
                color = Color(0xFF1e88c1),
                { navController.navigate("login") })
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
            Toast.makeText(context, state.value?.isSuccess, Toast.LENGTH_SHORT).show()
            navController.navigate("dashboard")
        }
    }
}


