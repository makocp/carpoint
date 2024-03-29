package com.example.carpoint.screens.dashboard.BottomBarNavigation
/**
Author: Marin Sekic
Last Change: 25.06.2023
 */
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carpoint.screens.Dashboard.ProfileScreen.ProfileScreen
import com.example.carpoint.screens.Dashboard.SettingsScreen.SettingsScreen
import com.example.carpoint.screens.dashboard.homeScreen.HomeScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController, navControllerMain: NavHostController
) {
    NavHost(navController, startDestination = BottomNavObject.Home.route) {
        composable(BottomNavObject.Home.route) {
            HomeScreen()
        }
        composable(BottomNavObject.Profile.route) {
            ProfileScreen()
        }
        composable(BottomNavObject.Settings.route) {
            SettingsScreen(navController, navControllerMain)
        }
    }
}