package com.example.carpoint.Screens.LogIn

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carpoint.Authentication.IAuthentication
import com.example.carpoint.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val auth : IAuthentication
) : ViewModel() {
    val _logInState = Channel<LogInState>()
    val logInState = _logInState.receiveAsFlow()

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
                    _logInState.send(LogInState(isError = result.message))
                }
            }
        }
    }
}