package com.example.carpoint.Authentication

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.carpoint.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAuthentication {
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.success(result))
        }.catch {
            emit(Resource.error(it.message.toString()))
        }
    }

    override fun createUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.success(result))
        }.catch {
            emit(Resource.error(it.message.toString()))
        }
    }
}