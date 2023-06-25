/**
IAuthRepositoryImpl.kt
Description: Implementation of the IAuthentication interface using Firebase authentication.
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
package com.example.carpoint.authentication

import com.example.carpoint.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Implementation of the [IAuthentication] interface using Firebase authentication.
 *
 * @param firebaseAuth The instance of [FirebaseAuth] for authentication.
 */
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAuthentication {
    /**
     * Logs in a user with the specified email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A [Flow] emitting [Resource] objects containing [AuthResult] indicating the login operation status.
     */
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.success(result))
        }.catch {
            emit(Resource.error(it.message.toString()))
        }
    }

    /**
     * Creates a new user with the specified email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A [Flow] emitting [Resource] objects containing [AuthResult] indicating the user creation operation status.
     */
    override fun createUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.success(result))
        }.catch {
            emit(Resource.error(it.message.toString()))
        }
    }

    /**
     * Sends a reset password email to the specified email address.
     *
     * @param email The email address to which the reset password email will be sent.
     */
    override fun sendResetPasswordMail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
    }

    /**
     * Retrieves the current user's ID from the authentication service.
     *
     * @return The current user's ID if available, or null if not authenticated.
     */
    override fun getCurrentUserIdFromAuth(): String? {
        return firebaseAuth.currentUser?.uid
    }
}
