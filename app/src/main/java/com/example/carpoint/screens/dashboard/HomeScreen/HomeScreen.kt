/**
Author: Mohammed Alamer, Marin Sekic
Last Change: 25.06.2023
 */

package com.example.carpoint.screens.dashboard.homeScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carpoint.R
import com.example.carpoint.dataBaseModels.NoteDb
import com.example.carpoint.models.Note
import com.example.carpoint.screens.dashboard.HomeScreen.HomeScreenViewmodel
import com.example.carpoint.utils.AddDivider
import com.example.carpoint.utils.AddFloatingActionButton
import com.example.carpoint.utils.AddText
import com.example.carpoint.utils.CreateButton
import com.example.carpoint.utils.CreateInputField
import com.example.carpoint.utils.NoteCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState",
    "CoroutineCreationDuringComposition"
)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewmodel = hiltViewModel()
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldsheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    var note by remember { mutableStateOf("") }
    var notes: MutableList<NoteDb> by mutableStateOf(mutableListOf())


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp),
        content = {
            BottomSheetScaffold(
                scaffoldState = scaffoldsheetState,
                sheetContent = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        AddDivider(padding = 30)
                        AddText(text = R.string.addNote, fontsize = 60, color = Color.Black)
                        CreateInputField(
                            placeholderResId = R.string.enterYourNote,
                            value = note,
                            onTextChanged = { note = it })
                        CreateButton(R.string.submit) {
                            if (note.isNotEmpty()) {
                                scope.launch() {
                                    viewModel.createNote(
                                        viewModel.getCurrentUserId(),
                                        Note(Date(), note)
                                    )
                                    note = ""
                                    notes = viewModel.getNotes().toMutableList()
                                    sheetState.collapse()
                                }
                            } else {
                                scope.launch {
                                    sheetState.collapse()
                                }
                            }
                        }
                    }
                },
                sheetBackgroundColor = Color.Gray,
                sheetPeekHeight = 0.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    scope.launch {
                        notes = viewModel.getNotes().toMutableList()
                    }
                    if (notes.isEmpty()) {
                        AddText(text = R.string.noNotesYet, fontsize = 30, color = Color.Gray)
                    } else {
                        LazyColumn {
                            items(notes.size) { index ->
                                val note = notes[index]
                                NoteCard(date = note.date, note = note.note) {
                                    scope.launch {
                                        notes = viewModel.deleteNoteAndFetchNotes(note)
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }, floatingActionButton = {
            AddFloatingActionButton {
                scope.launch {
                    if (sheetState.isCollapsed) {
                        sheetState.expand()
                    } else {
                        sheetState.collapse()
                    }
                }
            }
        }
    )
}
