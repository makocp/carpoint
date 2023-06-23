package com.example.carpoint.screens.Dashboard.SettingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(navController: NavHostController, navControllerMain: NavHostController){

    val loginSharedPref =
        LocalContext.current.getSharedPreferences(
            com.example.carpoint.sharedPreferences.SharedPreferences.LOGIN_PREF.prefName,
            0
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Settings",
            fontSize = 100.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = {
                with (loginSharedPref.edit()){
                    clear()
                    apply()
                }
                navControllerMain.navigate("login")
            }
        ) {
            Text(text = "Logout")
        }
    }
}