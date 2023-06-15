package com.example.carpoint.Screens.Dashboard.BottomBarNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carpoint.Screens.Dashboard.HomeScreen
import com.example.carpoint.Screens.Dashboard.ProfileScreen
import com.example.carpoint.Screens.Dashboard.SettingsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController)
{
    NavHost(navController, startDestination = BottomNavObject.Home.route){
        composable(BottomNavObject.Home.route){
            HomeScreen()
        }
        composable(BottomNavObject.Profile.route){
            ProfileScreen()
        }
        composable(BottomNavObject.Settings.route){
            SettingsScreen()
        }
    }
}