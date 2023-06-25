package com.example.carpoint.screens.logIn
/**
LoginViewModel.kt
Description: Viewmodel for the Login Screen.
Author: Marin Sekic
Last Change: 25.06.2023
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carpoint.authentication.IAuthentication
import com.example.carpoint.dataBase.IDatabaseHandler
import com.example.carpoint.dataBaseModels.UserDb
import com.example.carpoint.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * ViewModel class responsible for handling login-related functionality.
 *
 * @param auth The instance of [IAuthentication] for authentication operations.
 */
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val auth: IAuthentication,
    private val database: IDatabaseHandler
) : ViewModel() {
    private val _logInState = Channel<LogInState>()
    val logInState = _logInState.receiveAsFlow()

    /**
     * Logs in a user with the specified email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     */
    fun loginUser(email: String, password: String) = viewModelScope.launch {
        auth.loginUser(email, password).collect { result ->
            when (result) {
                is Resource.success -> {
                    _logInState.send(LogInState(isSuccess = "Sign In Success"))
                }

                is Resource.loading -> {
                    _logInState.send(LogInState(isLoading = true))
                }

                is Resource.error -> {
                    _logInState.send(LogInState(isError = "Something went wrong!"))
                }
            }
        }
    }

    /**
     * Retrieves the current user ID.
     *
     * @return The current user ID if available, empty string otherwise.
     */
    fun getCurrentUserId(): String {
        val userId = auth.getCurrentUserIdFromAuth()
        return userId ?: ""
    }

    /**
     * Validates the email and password.
     *
     * @param email The email to validate.
     * @param password The password to validate.
     * @return `true` if both email and password are valid, `false` otherwise.
     */
    private fun isCredentialsValid(email: String, password: String): Boolean {
        val isEmailValid = isValidEmail(email)
        val isPasswordValid = isValidPassword(password)
        return isEmailValid && isPasswordValid
    }

    /**
     * Validates the email format using a regular expression.
     *
     * @param email The email to validate.
     * @return `true` if the email is valid, `false` otherwise.
     */
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
        return email.matches(emailRegex)
    }

    /**
     * Validates the password length.
     *
     * @param password The password to validate.
     * @return `true` if the password is valid, `false` otherwise.
     */
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    /**
     * Logs in a user with the specified email and password, if the credentials are valid.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A message indicating the result of the login attempt.
     */
    fun loginWithValidCredentials(email: String, password: String): Int {
        if (isCredentialsValid(email, password)) {
            loginUser(email, password)
            return 1
        } else {
            return 0
        }
    }

    suspend fun getUser(): UserDb {
        val userId = getCurrentUserId()
        return suspendCoroutine { continuation ->
            database.getUser(userId) { userDb ->
                try {
                    continuation.resume(userDb)
                } catch (e: IllegalStateException) {

                }
            }
        }
    }

}
