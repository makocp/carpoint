package com.example.carpoint.dataBase

import com.example.carpoint.models.User

interface IDatabaseHandler {
    fun createUser(uid: String, user: User)
    fun editUser(user: User)
    fun deleteUser(email: String)
    fun getUser(email: String): User
    fun getUserImage(uid: String)
    fun processImageFromDataBase(base64String: String)
    fun uploadImage(uid: String, base64Image: String)
}