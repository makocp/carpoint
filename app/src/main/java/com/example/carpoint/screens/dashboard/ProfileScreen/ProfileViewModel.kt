package com.example.carpoint.screens.Dashboard.ProfileScreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.carpoint.authentication.IAuthentication
import com.example.carpoint.dataBase.IDatabaseHandler
import com.example.carpoint.dataBaseModels.NoteDb
import com.example.carpoint.dataBaseModels.UserDb
import com.example.carpoint.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: IAuthentication,
    private val database: IDatabaseHandler
) : ViewModel() {

    fun getCurrentUserId(): String {
        val userId = auth.getCurrentUserIdFromAuth()
        if (userId != null) {
            return userId
        }
        return ""
    }

    fun retrieveImageFromDatabase() {
        database.getUserImage(getCurrentUserId())
    }

    fun uploadPictureToDatabase(uriForImageDisplay: Uri?, context: Context) {
        val base64ImageString = convertUriToBase64(uriForImageDisplay, context)
        database.uploadImage(getCurrentUserId(), base64ImageString)
    }

    fun convertUriToBase64(uri: Uri?, context: Context): String {
        return uri.let { uri ->
            val inputStream = uri?.let { context.contentResolver.openInputStream(it) }
            val byteArray = inputStream?.readBytes()
            inputStream?.close()
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
    }

    fun decodeBase64StringToBitmap(base64String: String): Bitmap {
        Log.d("PICTURE_BYTES5", base64String)
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        Log.d("PICTURE_BYTES6", decodedBytes.size.toString())
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    suspend fun getUser(): UserDb {
        val userId = getCurrentUserId()
        return suspendCoroutine { continuation ->
            database.getUser(userId) { userDb ->
                continuation.resume(userDb)
            }
        }
    }
}