package com.example.carpoint.screens.dashboard.HomeScreen

import androidx.lifecycle.ViewModel
import com.example.carpoint.authentication.IAuthentication
import com.example.carpoint.dataBase.IDatabaseHandler
import com.example.carpoint.dataBase.IDatabaseHandlerImpl
import com.example.carpoint.dataBaseModels.NoteDb
import com.example.carpoint.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewmodel @Inject constructor(
    private val data: IDatabaseHandler
) : ViewModel() {

    fun createNote(note: Note) {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val formattedDate = dateFormat.format(note.date)
        data.createNote(NoteDb(formattedDate, note.note))
    }



}