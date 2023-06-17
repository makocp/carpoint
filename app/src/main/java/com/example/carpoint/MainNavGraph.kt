package com.example.carpoint

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carpoint.Screens.Dashboard.DashboardScreen
import com.example.carpoint.Screens.LogIn.LogInScreen
import com.example.carpoint.Screens.ResetPassword.ResetPassword
import com.example.carpoint.Screens.ResetPassword.ResetPasswordTransmission
import com.example.carpoint.Screens.SignUp.SignUpScreen

@Composable
fun MainNavGraph(navController: NavHostController){
    //TODO: routes als Constants anlegen.
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LogInScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("resetpasswordTransmission") { ResetPasswordTransmission(navController) }
        composable("resetpassword"){ ResetPassword(navController) }
        composable("dashboard") { DashboardScreen(navController) }
    }
}