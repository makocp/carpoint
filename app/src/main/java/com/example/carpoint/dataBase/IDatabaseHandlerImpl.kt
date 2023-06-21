package com.example.carpoint.dataBase

import com.example.carpoint.models.User
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

/**
 * Implementation of the [IDatabaseHandler] interface using Firebase Realtime Database.
 *
 * @param firebaseDatabase The instance of [FirebaseDatabase] for database operations.
 */
class IDatabaseHandlerImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : IDatabaseHandler {

    /**
     * Creates a new user in the database.
     *
     * @param user The user object to be created.
     */
    override fun createUser(user: User) {
        firebaseDatabase.goOnline()
        val userRef = firebaseDatabase.getReference("users")
        val newRef = userRef.push()
        newRef.setValue(user)
    }

    /**
     * Edits an existing user in the database.
     *
     * @param user The updated user object.
     */
    override fun editUser(user: User) {
        TODO("Not yet implemented")
    }

    /**
     * Deletes a user from the database using the provided email address.
     *
     * @param email The email address of the user to be deleted.
     */
    override fun deleteUser(email: String) {
        TODO("Not yet implemented")
    }

    /**
     * Retrieves a user from the database based on the provided email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The user object corresponding to the provided email address.
     */
    override fun getUser(email: String): User {
        TODO("Not yet implemented")
    }
}
