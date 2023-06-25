package com.example.carpoint
/**
NavControllerViewModel.kt
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class NavControllerViewModel (
    private val navController: NavController
) : ViewModel() {}