package com.example.carpoint.dataBase

import android.graphics.BitmapFactory
import android.util.Base64
import com.example.carpoint.dataBaseModels.NoteDb
import com.example.carpoint.dataBaseModels.UserDb
import com.example.carpoint.models.Note
import com.example.carpoint.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
    override fun createUser(uid: String, user: User) {
        val userDb = UserDb(uid, user.name, user.email, user.profileImage)
        firebaseDatabase.goOnline()
        val userRef = firebaseDatabase.getReference("users").child(uid)
        userRef.setValue(userDb)
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

    override fun getUserImage(uid: String) {
        firebaseDatabase.goOnline()
        firebaseDatabase.reference.child("users").child(uid)
            .child("profileImage").get().addOnSuccessListener {
                processImageFromDataBase(it.value.toString())
            }
    }

    override fun processImageFromDataBase(base64String: String) {
        val decodeBytes = Base64.decode(base64String, Base64.DEFAULT)
        var bitmap = BitmapFactory.decodeByteArray(decodeBytes, 0, decodeBytes.size)
    }

    override fun uploadImage(uid: String, base64Image: String) {
        firebaseDatabase.goOnline()
        firebaseDatabase.reference.child("users").child(uid)
            .child("profileImage").setValue(base64Image)
    }

    override fun createNote(note: NoteDb) {
        firebaseDatabase.goOnline()
        val userRef = firebaseDatabase.getReference("notes")
        val newRef = userRef.push()
        newRef.setValue(note)
    }


}