package com.example.carpoint.dataBase

import com.example.carpoint.models.User
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class IDatabaseHandlerImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : IDatabaseHandler {

    override fun createUser(uid: String, user: User) {
        val userDb = UserDb(uid, user.name, user.email, user.profileImage)
        firebaseDatabase.goOnline()
        val userRef = firebaseDatabase.getReference("users").child(uid)
        userRef.setValue(userDb)
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