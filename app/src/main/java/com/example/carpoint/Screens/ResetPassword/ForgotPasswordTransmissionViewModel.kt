package com.example.carpoint.Screens.ResetPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carpoint.Authentication.IAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForgotPasswordTransmissionViewModel @Inject constructor(
    private val auth: IAuthentication
) : ViewModel() {
    fun sendResetEmail(email: String) {
        viewModelScope.launch {
            auth.sendResetPasswordMail(email)
        }
    }
}