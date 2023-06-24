package com.example.carpoint.screens.dashboard.HomeScreen

import androidx.lifecycle.ViewModel
import com.example.carpoint.authentication.IAuthentication
import com.example.carpoint.dataBase.IDatabaseHandler
import com.example.carpoint.dataBase.IDatabaseHandlerImpl
import com.example.carpoint.dataBaseModels.NoteDb
import com.example.carpoint.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class HomeScreenViewmodel @Inject constructor(
    private val data: IDatabaseHandler,
    private val auth: IAuthentication
) : ViewModel() {

    fun createNote(uid :String,note: Note) {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val formattedDate = dateFormat.format(note.date)
        data.createNote(uid,NoteDb(formattedDate, note.note))
    }


    fun getCurrentUserId(): String {
        val userId = auth.getCurrentUserIdFromAuth()
        if (userId != null) {
            return userId
        }
        return ""
    }
    suspend fun getNotes(): List<NoteDb> = withContext(Dispatchers.Default) {
        val uid = getCurrentUserId()
        return@withContext suspendCoroutine<List<NoteDb>> { continuation ->
            data.getNotes(uid) { notesList ->
                continuation.resume(notesList)
            }
        }
    }
}