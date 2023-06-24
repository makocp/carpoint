package com.example.carpoint.screens.Dashboard.SettingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.carpoint.R
import com.example.carpoint.screens.dashboard.SettingsScreen.SettingsViewModel
import com.example.carpoint.utils.CreateButton
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavHostController,
    navControllerMain: NavHostController,
    viewmodel: SettingsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val loginSharedPref =
        LocalContext.current.getSharedPreferences(
            com.example.carpoint.sharedPreferences.SharedPreferences.LOGIN_PREF.prefName,
            0
        )
    val email = loginSharedPref.getString("email", null)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateButton(placeholderResId = R.string.changepassword) {
            scope.launch {
                if (email != null) {
                    viewmodel.changePassword(email)
                }
            }
        }
        CreateButton(placeholderResId = R.string.signout) {
            with(loginSharedPref.edit()) {
                clear()
                apply()
            }

            navControllerMain.navigate("login") {
                // Pop up to the login screen and clear the back stack
                popUpTo("login") {
                    inclusive = true
                }
            }
        }
    }
}