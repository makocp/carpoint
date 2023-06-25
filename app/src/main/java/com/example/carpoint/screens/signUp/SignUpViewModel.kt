package com.example.carpoint.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carpoint.authentication.IAuthentication
import com.example.carpoint.dataBase.IDatabaseHandler
import com.example.carpoint.models.User
import com.example.carpoint.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class responsible for handling sign-up-related functionality.
 *
 * @param auth The instance of [IAuthentication] for authentication operations.
 * @param database The instance of [IDatabaseHandler] for database operations.
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: IAuthentication,
    private val database: IDatabaseHandler
) : ViewModel() {
    private val _signUpState = Channel<SignUpState>()
    private var createdUid = ""
    val signUpState = _signUpState.receiveAsFlow()

    /**
     * Creates a new user with the specified email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     */
    fun createAccount(email: String, password: String) = viewModelScope.launch {
        if (isCredentialsValid(email, password)) {
            auth.createUser(email, password).collect { result ->
                createdUid = result.data?.user?.uid.toString()
                when (result) {
                    is Resource.success -> {
                        _signUpState.send(SignUpState(isSuccess = "Created Account Successfully"))
                    }
                    is Resource.loading -> {
                        _signUpState.send(SignUpState(isLoading = true))
                    }
                    is Resource.error -> {
                        _signUpState.send(SignUpState(isError = result.message))
                    }
                }
            }
        } else {
            _signUpState.send(SignUpState(isError = "Invalid email or password."))
        }
    }

    /**
     * Creates a new user in the database.
     *
     * @param user The user data to be stored.
     */
    fun createUser(user: User) {
        database.createUser(createdUid, user)
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
    fun isCredentialsValid(email: String, password: String): Boolean {
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
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
        return email.matches(emailRegex)
    }

    /**
     * Validates the password length.
     *
     * @param password The password to validate.
     * @return `true` if the password is valid, `false` otherwise.
     */
    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}
