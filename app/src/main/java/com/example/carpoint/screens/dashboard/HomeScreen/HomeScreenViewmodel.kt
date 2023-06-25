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
import kotlin.coroutines.resumeWithException
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

    suspend fun deleteNoteAndFetchNotes(note: NoteDb) :MutableList<NoteDb>  {
        val uid = getCurrentUserId()
        suspendCoroutine<Unit> { continuation ->
            data.deleteNote(uid, note.date, note.note) { success ->
                if (success) {
                    continuation.resume(Unit) // Resume coroutine when deletion is successful
                } else {
                    continuation.resumeWithException(Exception("Failed to delete note")) // Resume with an exception if deletion fails
                }
            }
        }

        // Deletion completed, now fetch the updated list of notes
        val n = getNotes().toMutableList()
        return n
    }


}