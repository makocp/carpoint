package com.example.carpoint

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carpoint.screens.LogIn.LogInScreen
import com.example.carpoint.screens.dashboard.DashboardScreen
import com.example.carpoint.screens.resetPassword.ForgotPasswordScreen
import com.example.carpoint.screens.signUp.SignUpScreen

/**
 * Composable function defining the main navigation graph of the app.
 *
 * @param navController The navigation controller used for navigating between screens.
 */
@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = "login"
    ) {
        composable("dashboard") {
            // Disables the back function from Dashboard to Login.
            BackHandler(true) {
                // todo: handle back button in dashboard screen. currently: nothing happens, disabled.
            }
            DashboardScreen(navController)
        }
        composable("login") { LogInScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("resetpassword") { ForgotPasswordScreen(navController) }
    }
}
