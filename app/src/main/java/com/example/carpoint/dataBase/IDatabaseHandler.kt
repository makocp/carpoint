package com.example.carpoint.dataBase

import com.example.carpoint.models.User

/**
 * Interface for handling database operations related to user data.
 */
interface IDatabaseHandler {
    /**
     * Creates a new user in the database.
     *
     * @param user The user object to be created.
     */
    fun createUser(user: User)

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
     * Retrieves a user from the database based on the provided email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The user object corresponding to the provided email address.
     */
    fun getUser(email: String): User
}
