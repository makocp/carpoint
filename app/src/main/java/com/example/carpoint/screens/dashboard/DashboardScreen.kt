package com.example.carpoint.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.carpoint.screens.dashboard.BottomBarNavigation.BottomNavGraph
import com.example.carpoint.screens.dashboard.BottomBarNavigation.BottomNavObject


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navControllerMain: NavHostController) {
    val navController = rememberNavController()

    // This is the manually created list.
//    val bottomNavObjects = listOf(
//        BottomNavObject.Home,
//        BottomNavObject.Profile,
//        BottomNavObject.Settings
//    )

    /*
    This is a dynamically created list of all subclasses of the sealed class BottomNavObject.
    The function gets all subclasses, casts it as instances and maps it into the list.
     */
    val bottomNavObjects = BottomNavObject::class.sealedSubclasses
        .mapNotNull { it.objectInstance }

    // TODO: Replace Scaffold (Row, Box).
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, bottomNavObjects)
        }
    ) {
        BottomNavGraph(navController, navControllerMain)
    }
}

@Composable
fun BottomBar(navController: NavHostController, bottomNavObjects: List<BottomNavObject>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color(0xFF1e88c1),
    ) {
        bottomNavObjects.forEach { item ->
            AddItem(
                item = item,
                currentDestination = currentDestination,
                navController = navController,


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
            Text(text = stringResource(id = item.title))
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
                launchSingleTop = true
            }
        }
    )
}