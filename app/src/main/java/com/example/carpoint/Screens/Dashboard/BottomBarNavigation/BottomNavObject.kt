package com.example.carpoint.Screens.Dashboard.BottomBarNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

// Sealed Class characteristics: Closed hierarchy, fixed set of (sub)classes.
sealed class BottomNavObject(
    val title: String,
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomNavObject(
        title = "Home",
        route = "home",
        icon = Icons.Default.Home
    )

    object Profile : BottomNavObject(
        title = "Profile",
        route = "profile",
        icon = Icons.Default.Person
    )

    object Settings : BottomNavObject(
        title = "Settings",
        route = "settings",
        icon = Icons.Default.Settings
    )
}
