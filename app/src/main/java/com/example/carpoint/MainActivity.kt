package com.example.carpoint

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carpoint.Screens.Dashboard.BottomBarNavigation.BottomNavObject
import com.example.carpoint.Screens.Dashboard.DashboardScreen
import com.example.carpoint.Screens.Dashboard.HomeScreen
import com.example.carpoint.Screens.LogIn.LogInScreen
import com.example.carpoint.Screens.ResetPassword.ResetPassword
import com.example.carpoint.Screens.ResetPassword.ResetPasswordTransmission
import com.example.carpoint.Screens.SignUp.SignUpScreen
import com.example.carpoint.ui.theme.CarPointTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
