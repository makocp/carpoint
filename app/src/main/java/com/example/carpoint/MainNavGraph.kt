package com.example.carpoint

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carpoint.screens.dashboard.DashboardScreen
import com.example.carpoint.screens.logIn.LogInScreen
import com.example.carpoint.screens.resetPassword.ResetPassword
import com.example.carpoint.screens.resetPassword.ResetPasswordTransmission
import com.example.carpoint.screens.signUp.SignUpScreen

@Composable
fun MainNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LogInScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("resetpasswordTransmission") { ResetPasswordTransmission(navController) }
        composable("resetpassword"){ ResetPassword(navController) }
        composable("dashboard") { DashboardScreen() }
    }
}