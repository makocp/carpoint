package com.example.carpoint.authentication

import com.example.carpoint.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

/**
 * Interface for authentication operations.
 */
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

    fun sendResetPasswordMail(email: String)

    fun getCurrentUserIdFromAuth(): String?
}
