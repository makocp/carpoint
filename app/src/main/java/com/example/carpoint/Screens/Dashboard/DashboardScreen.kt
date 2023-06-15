package com.example.carpoint.Screens.Dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.carpoint.Screens.Dashboard.BottomBarNavigation.BottomNavGraph
import com.example.carpoint.Screens.Dashboard.BottomBarNavigation.BottomNavObject


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val navController = rememberNavController()

    // TODO: Replace Scaffold (Row, Box).
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController)
    }


}

@Composable
fun BottomBar(navController: NavHostController) {
    val bottomNavObjects = listOf(
        BottomNavObject.Home,
        BottomNavObject.Profile,
        BottomNavObject.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation() {
        bottomNavObjects.forEach { item ->
            AddItem(
                item = item,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomNavObject,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = item.title)
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = "Navigation Item",
            )
        },
//        selected = currentDestination?.hierarchy?.any {
//            it.route == item.route
//        } == true,
        selected = currentDestination?.route == item.route,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(item.route) {

                // Pops to start destination before navigating.
                popUpTo(navController.graph.findStartDestination().id)

                // Allows only one copy of the same Screen on top of backstack.
                // TODO: View Live Backstack
                launchSingleTop = true
            }
        }
    )
}