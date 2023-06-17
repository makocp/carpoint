package com.example.carpoint

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class NavControllerViewModel (
    private val navController: NavController
) : ViewModel() {}