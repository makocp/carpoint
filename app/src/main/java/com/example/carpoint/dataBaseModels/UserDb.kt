/**
UserDb.kt
Description: User object for database.
Author: Marin Sekic
Last Change: 25.06.2023
 */
package com.example.carpoint.dataBaseModels

data class UserDb(
    val id: String,
    val name: String,
    val email: String,
    val profileImage : String
)