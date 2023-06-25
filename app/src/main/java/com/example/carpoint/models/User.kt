/**
User.kt
Description: User object.
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
package com.example.carpoint.models

import java.util.UUID

/**
 * Data class representing a user.
 *
 * @property id The unique identifier of the user.
 * @property name The name of the user.
 * @property email The email address of the user.
 * @property profileImage The profile image URL of the user.
 */
data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val profileImage: String
)
