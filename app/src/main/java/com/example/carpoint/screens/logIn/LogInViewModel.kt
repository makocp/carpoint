package com.example.carpoint.screens.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carpoint.authentication.IAuthentication
import com.example.carpoint.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class responsible for handling login-related functionality.
 *
 * @param auth The instance of [IAuthentication] for authentication operations.
 */
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val auth: IAuthentication) : ViewModel() {
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
}
