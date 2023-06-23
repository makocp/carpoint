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
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: IAuthentication,
    private val database: IDatabaseHandler) : ViewModel() {
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
    }

    fun createUser(user: User){
        database.createUser(createdUid ,user)
    }

    fun getCurrentUserId(): String {
        val userId = auth.getCurrentUserIdFromAuth()
        if (userId != null) {
            return userId
        }
        return ""
    }
}
