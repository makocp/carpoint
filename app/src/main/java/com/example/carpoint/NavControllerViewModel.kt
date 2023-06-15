package com.example.carpoint

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavControllerViewModel @Inject constructor(
    private val navController: NavController
) : ViewModel() {}