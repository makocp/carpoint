package com.example.carpoint

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carpoint.Screens.Dashboard.DashboardScreen
import com.example.carpoint.Screens.LogIn.LogInScreen
import com.example.carpoint.Screens.ResetPassword.ResetPassword
import com.example.carpoint.Screens.ResetPassword.ResetPasswordTransmission
import com.example.carpoint.Screens.SignUp.SignUpScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") { LogInScreen(navController) }
                composable("signup") { SignUpScreen(navController) }
                composable("resetpasswordTransmission") {ResetPasswordTransmission(navController)}
                composable("resetpassword"){ResetPassword(navController)}
                composable("dashboard") { DashboardScreen() }
            }
        }
    }
}
