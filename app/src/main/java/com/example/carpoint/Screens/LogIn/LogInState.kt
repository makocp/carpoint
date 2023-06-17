package com.example.carpoint.Screens.LogIn


data class LogInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)