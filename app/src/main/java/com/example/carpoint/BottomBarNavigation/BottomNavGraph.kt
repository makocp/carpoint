package com.example.carpoint.BottomBarNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carpoint.Dashboard.HomeScreen
import com.example.carpoint.Dashboard.ProfileScreen
import com.example.carpoint.Dashboard.SettingsScreen

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