package com.example.carpoint.screens.resetPassword
/**
ForgotPasswordViewModel.kt
Description: Viewmodel for the forgotpassword Screen.
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carpoint.authentication.IAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val auth: IAuthentication
) : ViewModel() {
    fun sendResetEmail(email: String) {
        viewModelScope.launch {
            auth.sendResetPasswordMail(email)
        }
    }
}