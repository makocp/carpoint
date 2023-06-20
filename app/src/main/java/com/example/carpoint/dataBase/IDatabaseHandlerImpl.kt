package com.example.carpoint.dataBase

import com.example.carpoint.models.User
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class IDatabaseHandlerImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : IDatabaseHandler {

    override fun createUser(user: User) {
        firebaseDatabase.goOnline()
        val userRef = firebaseDatabase.getReference("users")
        val newRef = userRef.push()
        newRef.setValue(user)
    }

    override fun editUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(email: String) {
        TODO("Not yet implemented")
    }

    override fun getUser(email: String): User {
        TODO("Not yet implemented")
    }
}