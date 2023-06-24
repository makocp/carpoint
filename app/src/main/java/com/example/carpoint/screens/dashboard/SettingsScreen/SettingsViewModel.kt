package com.example.carpoint.screens.dashboard.SettingsScreen

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