package com.example.carpoint.Screens.LogIn

/**
 * Represents the state of the login process.
 *
 * @param isLoading Indicates whether the login process is currently in progress.
 * @param isSuccess Contains a success message if the login process is successful.
 * @param isError Contains an error message if an error occurs during the login process.
 */
data class LogInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
