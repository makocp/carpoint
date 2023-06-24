package com.example.carpoint.screens.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.carpoint.R
import com.example.carpoint.models.User
import com.example.carpoint.sharedPreferences.SharedPreferences
import com.example.carpoint.utils.AddClickableText
import com.example.carpoint.utils.AddDivider
import com.example.carpoint.utils.AddText
import com.example.carpoint.utils.CreateButton
import com.example.carpoint.utils.CreatePasswordField
import com.example.carpoint.utils.CreateTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {

    val scope = rememberCoroutineScope()
    val state = viewModel.signUpState.collectAsState(initial = null)
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // SharedPref for Login State is here created/declared.
    val loginSharedPref =
        LocalContext.current.getSharedPreferences(SharedPreferences.LOGIN_PREF.prefName, 0)


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AddText(text = R.string.createAccount, fontsize = 30, color = Color.Black)
        CreateTextField(
            placeholderResId = R.string.email,
            leadingIcon = Icons.Default.Email,
            value = email,
            onTextChanged = { email = it })
        CreateTextField(
            placeholderResId = R.string.Username,
            leadingIcon = Icons.Default.Person,
            value = username,
            onTextChanged = { username = it })
        CreatePasswordField(
            placeholderResId = R.string.password,
            leadingIcon = Icons.Default.Lock,
            value = password,
            onValueChange = { password = it })
        CreatePasswordField(
            placeholderResId = R.string.confirmPassword,
            leadingIcon = Icons.Default.Lock,
            value = confirmPassword,
            onValueChange = { confirmPassword = it })
        CreateButton(placeholderResId = R.string.createAccount) {
            if (password.equals(confirmPassword)) {
                scope.launch {
                    viewModel.createAccount(email, password)
                }
            }
        }
        AddDivider(padding = 30)
        Row {
            AddText(text = R.string.alreadyHaveAccount, fontsize = 15, Color.Gray)
            AddClickableText(
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
        } else {
            LaunchedEffect(state.value) {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    viewModel.createUser(User(name = username, email = email, profileImage = ""))
                    navController.navigate("dashboard") {
                        // Pops to start destination before navigating.
                        // -> If user is on Home Screen and goes back -> he goes directly to login and not create account.
                        popUpTo(navController.graph.findStartDestination().id)
                        // Allows only one copy of the same Screen on top of backstack.
                        launchSingleTop = true
                        // Put in the current User Id into Shared Preference to check later on, if logged in.
                        // Before writing the UID to Shared Preferences, it gets cleared/all logins removed.
                        // Gets reseted, when Shared Preferences get deleted (Application Reinstallation) or at Logout.
                        with(loginSharedPref.edit()) {
                            clear()
                            apply()
                            putString("loggedIn", viewModel.getCurrentUserId())
                            putString("email", email)
                            apply()
                        }
                    }
                }
            }
        }
    }
}


