package com.example.carpoint.screens.dashboard.HomeScreen

/**
Author: Marin Sekic
Last Change: 25.06.2023
 */

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
/**
 * ViewModel class for the home screen.
 * Responsible for managing the business logic and data operations related to the home screen.
 */
@HiltViewModel
class HomeScreenViewmodel @Inject constructor(
    private val data: IDatabaseHandler,
    private val auth: IAuthentication
) : ViewModel() {

    /**
     * Creates a new note in the database.
     *
     * @param uid The ID of the user creating the note.
     * @param note The note to be created.
     */
    fun createNote(uid: String, note: Note) {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val formattedDate = dateFormat.format(note.date)
        data.createNote(uid, NoteDb(formattedDate, note.note))
    }

    /**
     * Retrieves the current user ID from the authentication component.
     *
     * @return The current user ID, or an empty string if the user ID is not available.
     */
    fun getCurrentUserId(): String {
        val userId = auth.getCurrentUserIdFromAuth()
        if (userId != null) {
            return userId
        }
        return ""
    }

    /**
     * Retrieves the list of notes from the database for the current user.
     *
     * @return A list of NoteDb objects representing the notes.
     */
    suspend fun getNotes(): List<NoteDb> = withContext(Dispatchers.Default) {
        val uid = getCurrentUserId()
        return@withContext suspendCoroutine<List<NoteDb>> { continuation ->
            data.getNotes(uid) { notesList ->
                continuation.resume(notesList)
            }
        }
    }

    /**
     * Deletes a note from the database and fetches the updated list of notes.
     *
     * @param note The note to be deleted.
     * @return The updated list of notes as a MutableList<NoteDb>.
     * @throws Exception if the note deletion fails.
     */
    suspend fun deleteNoteAndFetchNotes(note: NoteDb): MutableList<NoteDb> {
        val uid = getCurrentUserId()
        suspendCoroutine<Unit> { continuation ->
            data.deleteNote(uid, note.date, note.note) { success ->
                if (success) {
                    continuation.resume(Unit)
                } else {
                    continuation.resumeWithException(Exception("Failed to delete note"))
                }
            }
        }

        val n = getNotes().toMutableList()
        return n
    }
}