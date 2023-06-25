package com.example.carpoint.screens.dashboard.BottomBarNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.carpoint.R
/**
Author: Marin Sekic
Last Change: 25.06.2023
 */

// Sealed Class characteristics: Closed hierarchy, fixed set of (sub)classes.
sealed class BottomNavObject(
    val title: Int,
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomNavObject(
        title = R.string.home,
        route = "home",
        icon = Icons.Default.Home
    )

    object Profile : BottomNavObject(
        title = R.string.profile,
        route = "profile",
        icon = Icons.Default.Person
    )

    object Settings : BottomNavObject(
        title = R.string.settings,
        route = "settings",
        icon = Icons.Default.Settings
    )
}
