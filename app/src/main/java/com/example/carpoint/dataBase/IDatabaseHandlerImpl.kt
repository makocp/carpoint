package com.example.carpoint.dataBase

import android.graphics.BitmapFactory
import android.util.Base64
import com.example.carpoint.dataBaseModels.UserDb
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

    override fun getUserImage(uid: String) {
        firebaseDatabase.goOnline()
        firebaseDatabase.reference.child("users").child(uid)
            .child("profileImage").get().addOnSuccessListener {
                processImageFromDataBase(it.value.toString())
            }
    }

    override fun processImageFromDataBase(base64String: String) {
        val decodeBytes = Base64.decode(base64String, Base64.DEFAULT)
        var bitmap = BitmapFactory.decodeByteArray(decodeBytes,0,decodeBytes.size)
    }

    override fun uploadImage(uid: String, base64Image: String) {
        firebaseDatabase.goOnline()
        firebaseDatabase.reference.child("users").child(uid)
            .child("profileImage").setValue(base64Image)
    }
}