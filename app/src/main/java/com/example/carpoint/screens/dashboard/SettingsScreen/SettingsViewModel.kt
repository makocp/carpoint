package com.example.carpoint.screens.dashboard.SettingsScreen

/**
SettingsViewModel.kt
Description: Viewmodel for the Setting Screen.
Author: Mohammed Alamer
Last Change: 25.06.2023
 */

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import com.example.carpoint.authentication.IAuthentication
import com.example.carpoint.dataBase.IDatabaseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val auth: IAuthentication) : ViewModel(){
        fun changePassword(email: String){
            auth.sendResetPasswordMail(email = email)
        }
    }