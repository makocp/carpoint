package com.example.carpoint.Screens.LogIn

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
) : ViewModel() {

    fun log(){
        Log.d("TAG", "Your log message")
    }
}