/**
IDatabaseHandler.kt
Description: Interface for handling database operations related to user data.
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
package com.example.carpoint.dataBase

import com.example.carpoint.dataBaseModels.NoteDb
import com.example.carpoint.dataBaseModels.UserDb
import com.example.carpoint.models.User

/**
 * Interface for handling database operations related to user data.
 */
interface IDatabaseHandler {
    /**
     * Creates a new user in the database.
     *
     * @param uid The Firebase Auth Uid.
     * @param user The user object to be created.
     */
    fun createUser(uid: String, user: User)

    /**
     * Edits an existing user in the database.
     *
     * @param user The updated user object.
     */
    fun editUser(user: User)

    /**
     * Deletes a user from the database using the provided email address.
     *
     * @param email The email address of the user to be deleted.
     */
    fun deleteUser(email: String)

    /**
     * Retrieves a user from the database based on the provided uid.
     *
     * @param uid The uid of the user to retrieve.
     * @param callback The callback function to handle the retrieved user object.
     */
    fun getUser(uid: String, callback: (UserDb) -> Unit)

    /**
     * Retrieves the image of a user from the database based on the provided uid.
     *
     * @param uid The uid of the user to retrieve the image for.
     */
    fun getUserImage(uid: String)

    /**
     * Processes the image retrieved from the database.
     *
     * @param base64String The base64 encoded string representing the image.
     */
    fun processImageFromDataBase(base64String: String)

    /**
     * Uploads an image to the database for the specified user.
     *
     * @param uid The uid of the user to upload the image for.
     * @param base64Image The base64 encoded string representing the image to upload.
     */
    fun uploadImage(uid: String, base64Image: String)

    /**
     * Creates a new note in the database for the specified user.
     *
     * @param uid The uid of the user to create the note for.
     * @param note The note object to be created.
     */
    fun createNote(uid: String, note: NoteDb)

    /**
     * Retrieves all notes for the specified user from the database.
     *
     * @param uid The uid of the user to retrieve the notes for.
     * @param callback The callback function to handle the retrieved list of notes.
     */
    fun getNotes(uid: String, callback: (List<NoteDb>) -> Unit)

    /**
     * Deletes a note from the database for the specified user.
     *
     * @param uid The uid of the user to delete the note from.
     * @param date The date of the note to be deleted.
     * @param note The content of the note to be deleted.
     * @param callback The callback function to handle the result of the delete operation.
     */
    fun deleteNote(uid: String, date: String, note: String, callback: (Boolean) -> Unit)
}
