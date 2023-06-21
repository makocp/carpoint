package com.example.carpoint

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carpoint.Screens.LogIn.LogInScreen
import com.example.carpoint.screens.dashboard.DashboardScreen
import com.example.carpoint.screens.resetPassword.ForgotPasswordScreen
import com.example.carpoint.screens.signUp.SignUpScreen

@Composable
fun MainNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LogInScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("resetpassword") { ForgotPasswordScreen(navController) }
        composable("dashboard") { DashboardScreen() }
    }
}