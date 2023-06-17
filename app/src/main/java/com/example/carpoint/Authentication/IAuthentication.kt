package com.example.carpoint.Authentication

import com.example.carpoint.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface IAuthentication {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun createUser(email: String, password: String): Flow<Resource<AuthResult>>
}