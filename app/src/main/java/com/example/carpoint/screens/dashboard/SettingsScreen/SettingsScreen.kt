/**
SettingsScreen.kt
Description: Composable function for the Settings screen.
Author: Marin Sekic
Last Change: 25.06.2023
 */
package com.example.carpoint.screens.Dashboard.SettingsScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.carpoint.R
import com.example.carpoint.screens.dashboard.SettingsScreen.SettingsViewModel
import com.example.carpoint.utils.AddText
import com.example.carpoint.utils.CreateButton
import com.example.carpoint.utils.NextStepsComponent
import com.example.carpoint.utils.alertDialog
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavHostController,
    navControllerMain: NavHostController,
    viewmodel: SettingsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val loginSharedPref =
        context.getSharedPreferences(
            com.example.carpoint.sharedPreferences.SharedPreferences.LOGIN_PREF.prefName,
            0
        )
    val email = loginSharedPref.getString("email", null)
    var showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showDialog.value) {

            NextStepsComponent(
                nextSteps = listOf(
                    "Friendslist",
                    "Allow postings and create a news feed",
                    "chat function"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            AddText(text = R.string.privacyandsecurity, fontsize = 20, color = Color.Black)
            CreateButton(placeholderResId = R.string.changepassword) {
                scope.launch {
                    if (email != null) {
                        viewmodel.changePassword(email)
                    }
                    Toast.makeText(context, R.string.checkyouremail, Toast.LENGTH_SHORT).show()
                }
            }
            CreateButton(placeholderResId = R.string.signout) {
                showDialog.value = true
            }
        }

        if (showDialog.value) {
            alertDialog(showDialog = showDialog, placeholderResId = R.string.confirmLogout) {
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
}