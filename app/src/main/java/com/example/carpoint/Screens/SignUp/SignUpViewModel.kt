package com.example.carpoint.Screens.SignUp

import com.example.carpoint.Screens.LogIn.LogInState
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
class SignUpViewModel @Inject constructor(
    private val auth : IAuthentication
) : ViewModel() {
    val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()
    fun createUser(email: String, password: String) = viewModelScope.launch {
        auth.createUser(email, password).collect { result ->
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
    }
}