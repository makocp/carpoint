package com.example.carpoint.dataBase

import android.content.ContentValues.TAG
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.carpoint.dataBaseModels.NoteDb
import com.example.carpoint.dataBaseModels.UserDb
import com.example.carpoint.models.Note
import com.example.carpoint.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
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
    override fun getUser(uid : String,callback: (UserDb) -> Unit) {
        val userRef = firebaseDatabase.getReference("users").child(uid)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataSnapshotValue = dataSnapshot.value as? Map<String, String>
                val id = dataSnapshot.key ?: ""
                val name = dataSnapshotValue?.get("name") as? String ?: ""
                val email = dataSnapshotValue?.get("email") as? String ?: ""
                val profileImage = dataSnapshotValue?.get("profileImage") as? String ?: ""

                val user = UserDb(id, name, email, profileImage)
                if (user != null) {
                    callback(user)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "gettingUser: CANCELLED", databaseError.toException())
            }
        }
        userRef.addValueEventListener(postListener)

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
    override fun createNote(uid: String, note: NoteDb) {
        firebaseDatabase.goOnline()
        val noteRef = firebaseDatabase.getReference("notes").child(uid)
        val newNote = noteRef.push()
        newNote.setValue(note)
    }
    override fun getNotes(uid: String, callback: (List<NoteDb>) -> Unit) {
        val noteRef = firebaseDatabase.getReference("notes").child(uid)
        val notesList = mutableListOf<NoteDb>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (noteSnapshot in dataSnapshot.children) {
                    val noteId = noteSnapshot.key
                    val noteData = noteSnapshot.value as? Map<*, *> // Retrieve the note data as a Map

                    if (noteId != null && noteData != null) {
                        val noteDate = noteData["date"] as? String
                        val noteText = noteData["note"] as? String

                        if (noteDate != null && noteText != null) {
                            val note = NoteDb(noteDate, noteText)
                            notesList.add(note)
                        }
                    }
                }

                // Invoke the callback with the populated notesList
                callback(notesList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "gettingNotes:onCancelled", databaseError.toException())
            }
        }

        noteRef.addListenerForSingleValueEvent(postListener)
    }
}