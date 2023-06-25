package com.example.carpoint.screens.signUp
/**
SignUpState.kt
Author: Mohammed Alamer
Last Change: 25.06.2023
 */

/**
 * Represents the state of the sign-up process.
 *
 * @param isLoading Indicates whether the sign-up process is currently in progress.
 * @param isSuccess Contains a success message if the sign-up process is successful.
 * @param isError Contains an error message if an error occurs during the sign-up process.
 */
data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
