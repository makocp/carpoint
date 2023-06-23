package com.example.carpoint.Screens.LogIn

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.carpoint.R
import com.example.carpoint.screens.logIn.LogInViewModel
import com.example.carpoint.utils.AddClickableText
import com.example.carpoint.utils.AddDivider
import com.example.carpoint.utils.AddText
import com.example.carpoint.utils.CreateButton
import com.example.carpoint.utils.CreatePasswordField
import com.example.carpoint.utils.CreateTextField
import com.example.carpoint.utils.DisplayLogo
import com.example.carpoint.utils.IndicateProgressing
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
        DisplayLogo()
        AddText(text = R.string.logIn, fontsize = 30, color = Color.Black)
        CreateTextField(
            placeholderResId = R.string.email,
            leadingIcon = Icons.Default.Email,
            value = email,
            onTextChanged = { email = it })

        CreatePasswordField(
            placeholderResId = R.string.password,
            leadingIcon = Icons.Default.Lock,
            value = password,
            onValueChange = { password = it })

        AddClickableText(
            text = R.string.forgotPassword,
            fontsize = 15,
            color = Color(0xFF1e88c1),
            { navController.navigate("resetpassword") })

        CreateButton(R.string.logIn, { scope.launch { viewModel.loginUser(email, password) } })

        AddDivider(padding = 30)

        Row {
            AddText(text = R.string.dontHaveAccount, fontsize = 15, Color.Gray)
            AddClickableText(
                text = R.string.signUp,
                fontsize = 15,
                color = Color(0xFF1e88c1),
                { navController.navigate("signup") })

            if (state.value?.isLoading == true) {
                IndicateProgressing()
            }
            LaunchedEffect(state.value) {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    navController.navigate("dashboard")
                } else if (state.value?.isError?.isNotEmpty() == true) {
                    Toast.makeText(context, state.value?.isError, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
