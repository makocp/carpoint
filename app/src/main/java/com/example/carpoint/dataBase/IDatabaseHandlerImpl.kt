/**
IDatabaseHandlerImpl.kt
Description: Implementation of the IDatabaseHandler interface using Firebase Realtime Database.
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
package com.example.carpoint.dataBase

import android.content.ContentValues.TAG
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.carpoint.dataBaseModels.NoteDb
import com.example.carpoint.dataBaseModels.UserDb
import com.example.carpoint.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject


/**
 * Implementation of the [IDatabaseHandler] interface using Firebase Realtime Database.
 *
 * This class provides methods to perform various database operations such as creating a user,
 * editing a user, deleting a user, retrieving user information, uploading and retrieving user
 * profile images, creating notes, retrieving notes, and deleting notes.
 *
 * @param firebaseDatabase The instance of [FirebaseDatabase] for database operations.
 */
class IDatabaseHandlerImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : IDatabaseHandler {

    /**
     * Creates a new user in the database.
     *
     * @param uid The unique identifier of the user.
     * @param user The user object to be created.
     */
    override fun createUser(uid: String, user: User) {
        // Convert the user object to UserDb object
        val userDb = UserDb(uid, user.name, user.email, user.profileImage)

        // Bring the Firebase Database online (in case it was offline)
        firebaseDatabase.goOnline()

        // Create a reference to the "users/$uid" path in the Firebase database
        val userRef = firebaseDatabase.getReference("users").child(uid)

        // Set the value of the user reference to the userDb object
        userRef.setValue(userDb)
    }

    /**
     * Edits an existing user in the database.
     *
     * @param user The updated user object.
     */
    override fun editUser(user: User) {
        // TODO: Implement the logic to edit an existing user in the database
    }

    /**
     * Deletes a user from the database using the provided email address.
     *
     * @param email The email address of the user to be deleted.
     */
    override fun deleteUser(email: String) {
        // TODO: Implement the logic to delete a user from the database using the email address
    }

    /**
     * Retrieves a user from the database based on the provided user ID.
     *
     * @param uid The user ID of the user to retrieve.
     * @param callback The callback function to receive the retrieved user.
     */
    override fun getUser(uid: String, callback: (UserDb) -> Unit) {
        // Create a reference to the "users/$uid" path in the Firebase database
        val userRef = firebaseDatabase.getReference("users").child(uid)

        // Create a ValueEventListener to listen for changes in the userRef reference
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This function is called when the data at the reference changes. It receives a DataSnapshot object, which represents a snapshot of the data at that reference.

                // Extract the values from the dataSnapshot
                val dataSnapshotValue = dataSnapshot.value as? Map<String, String>
                val id = dataSnapshot.key ?: ""
                val name = dataSnapshotValue?.get("name") as? String ?: ""
                val email = dataSnapshotValue?.get("email") as? String ?: ""
                val profileImage = dataSnapshotValue?.get("profileImage") as? String ?: ""

                // Create a UserDb object with the extracted values
                val user = UserDb(id, name, email, profileImage)

                // Invoke the callback with the populated user object
                if (user != null) {
                    callback(user)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // This function is called if the listener is canceled or encounters an error

                // Log the error message
                Log.w(TAG, "gettingUser: CANCELLED", databaseError.toException())
            }
        }

        // Attach the postListener to the userRef reference to listen for changes
        userRef.addValueEventListener(postListener)
    }

    /**
     * Retrieves the profile image of a user from the database based on the provided user ID.
     *
     * @param uid The user ID of the user whose profile image to retrieve.
     */
    override fun getUserImage(uid: String) {
        // Bring the Firebase Database online (in case it was offline)
        firebaseDatabase.goOnline()

        // Create a reference to the "profileImage" field under the "users/$uid" path in the Firebase database
        val imageRef = firebaseDatabase.reference.child("users").child(uid).child("profileImage")

        // Retrieve the value of the profileImage field
        imageRef.get().addOnSuccessListener { snapshot ->
            // This function is called when the data retrieval is successful. It receives a DataSnapshot object, which represents a snapshot of the retrieved data.

            // Process the retrieved image value
            processImageFromDataBase(snapshot.value.toString())
        }
    }

    /**
     * Processes the retrieved image from the database.
     *
     * @param base64String The base64-encoded image string retrieved from the database.
     */
    override fun processImageFromDataBase(base64String: String) {
        // Decode the base64String into bytes
        val decodeBytes = Base64.decode(base64String, Base64.DEFAULT)

        // Decode the byte array into a Bitmap
        var bitmap = BitmapFactory.decodeByteArray(decodeBytes, 0, decodeBytes.size)
    }

    /**
     * Uploads the profile image of a user to the database.
     *
     * @param uid The user ID of the user whose profile image to upload.
     * @param base64Image The base64-encoded image string to upload.
     */
    override fun uploadImage(uid: String, base64Image: String) {
        // Bring the Firebase Database online (in case it was offline)
        firebaseDatabase.goOnline()

        // Create a reference to the "profileImage" field under the "users/$uid" path in the Firebase database
        val imageRef = firebaseDatabase.reference.child("users").child(uid).child("profileImage")

        // Set the value of the profileImage field to the provided base64Image
        imageRef.setValue(base64Image)
    }

    /**
     * Creates a new note for a user in the database.
     *
     * @param uid The user ID of the user to create the note for.
     * @param note The note object to be created.
     */
    override fun createNote(uid: String, note: NoteDb) {
        // Bring the Firebase Database online (in case it was offline)
        firebaseDatabase.goOnline()

        // Create a reference to the "notes" path in the Firebase database with the given user ID
        val noteRef = firebaseDatabase.getReference("notes").child(uid)

        // Generate a new unique key for the note
        val newNote = noteRef.push()

        // Set the value of the new note using the provided NoteDb object
        newNote.setValue(note)
    }

    /**
     * Retrieves the notes for a user from the database based on the provided user ID.
     *
     * @param uid The user ID of the user to retrieve the notes for.
     * @param callback The callback function to receive the retrieved notes.
     */
    override fun getNotes(uid: String, callback: (List<NoteDb>) -> Unit) {
        // Create a reference to the "notes" path in the Firebase database with the given user ID
        val noteRef = firebaseDatabase.getReference("notes").child(uid)

        // Create an empty mutable list to store the retrieved notes
        val notesList = mutableListOf<NoteDb>()

        // Create a ValueEventListener to listen for changes in the noteRef reference
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This function is called when the data at the reference changes. It receives a DataSnapshot object, which represents a snapshot of the data at that reference.

                for (noteSnapshot in dataSnapshot.children) {
                    // Loop through each child of the dataSnapshot, where each child represents a note

                    val noteId = noteSnapshot.key
                    val noteData = noteSnapshot.value as? Map<*, *> // Retrieve the note data as a Map

                    if (noteId != null && noteData != null) {
                        // Check if both the note ID and note data are not null

                        val noteDate = noteData["date"] as? String
                        val noteText = noteData["note"] as? String

                        if (noteDate != null && noteText != null) {
                            // Check if both the note date and note text are not null

                            val note = NoteDb(noteDate, noteText)
                            notesList.add(note)
                        }
                    }
                }

                // Invoke the callback with the populated notesList
                callback(notesList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // This function is called if the listener is canceled or encounters an error

                // Log the error message
                Log.w(TAG, "gettingNotes:onCancelled", databaseError.toException())
            }
        }

        // Attach the postListener to the noteRef reference to trigger a single value event
        noteRef.addListenerForSingleValueEvent(postListener)
    }

    /**
     * Deletes a note from the database for a user.
     *
     * @param uid The user ID of the user to delete the note for.
     * @param date The date of the note to be deleted.
     * @param note The content of the note to be deleted.
     * @param callback The callback function to receive the deletion status.
     */
    override fun deleteNote(uid: String, date: String, note: String, callback: (Boolean) -> Unit) {
        // Create a reference to the "notes" path in the Firebase database with the given user ID
        val noteRef = firebaseDatabase.getReference("notes").child(uid)

        // Attach a ValueEventListener to the noteRef reference, which listens for changes to the data at that reference
        noteRef.orderByChild("note").equalTo(note).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This function is called when the data at the reference changes. It receives a DataSnapshot object, which represents a snapshot of the data at that reference.

                var deletionSuccessful = false

                // Loop through each child of the dataSnapshot, where each child represents a note
                for (noteSnapshot in dataSnapshot.children) {
                    // Extract the data of the current note from the noteSnapshot
                    val noteData = noteSnapshot.value as? Map<*, *>
                    val noteDate = noteData?.get("date") as? String

                    // Check if the date of the current note matches the given date
                    if (noteDate == date) {
                        // Remove the reference to this note from the database
                        noteSnapshot.ref.removeValue()
                        deletionSuccessful = true
                        break
                    }
                }

                // Notify the callback about the success or failure of the deletion
                callback(deletionSuccessful)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // This function is called if the listener is canceled or encounters an error

                // Log the error message
                Log.w(TAG, "deletingNote:onCancelled", databaseError.toException())

                // Notify the callback about the failure of the deletion
                callback(false)
            }
        })
    }
}
