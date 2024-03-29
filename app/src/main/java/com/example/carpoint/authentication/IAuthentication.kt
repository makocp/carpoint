/**
IAuthentication.kt
Description: Interface for authentication operations.
Author: Mohammed Alamer
Last Change: 25.06.2023
 */

package com.example.carpoint.authentication

import com.example.carpoint.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface IAuthentication {
    /**
     * Logs in a user with the specified email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A [Flow] emitting [Resource] objects containing [AuthResult] indicating the login operation status.
     */
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>

    /**
     * Creates a new user with the specified email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A [Flow] emitting [Resource] objects containing [AuthResult] indicating the user creation operation status.
     */
    fun createUser(email: String, password: String): Flow<Resource<AuthResult>>

    /**
     * Sends a reset password email to the specified email address.
     *
     * @param email The email address to which the reset password email will be sent.
     */
    fun sendResetPasswordMail(email: String) {}

    /**
     * Retrieves the current user's ID from the authentication service.
     *
     * @return The current user's ID if available, or null if not authenticated.
     */
    fun getCurrentUserIdFromAuth(): String?
}
