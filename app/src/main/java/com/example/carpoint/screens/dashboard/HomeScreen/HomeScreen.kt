package com.example.carpoint.screens.Dashboard.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carpoint.models.Note
import com.example.carpoint.screens.dashboard.HomeScreen.HomeScreenViewmodel
import com.example.carpoint.screens.logIn.LogInViewModel
import com.example.carpoint.utils.AddFloatingActionButton
import com.example.carpoint.utils.NoteCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Date

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewmodel = hiltViewModel()
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteCard(date = "June 23, 2023", note = "This is a sample note.")
                NoteCard(date = "June 23, 2023", note = "This is a sample note.")
                NoteCard(date = "June 23, 2023", note = "This is a sample note.")
                NoteCard(date = "June 23, 2023", note = "This is a sample note.")
                NoteCard(date = "June 23, 2023", note = "This is a sample note.")
                NoteCard(date = "June 23, 2023", note = "This is a sample note.")
                NoteCard(date = "June 23, 2023", note = "This is a sample note.")
                NoteCard(date = "June 23, 2023", note = "This is a sample note.")
            }
        }, floatingActionButton = { AddFloatingActionButton {
            scope.launch {
                viewModel.createNote(
                    Note(
                        Date(), "Tesasdadadt"
                    )
                )
            }
        }}
    )
}

@Preview
@Composable
fun ProfileView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    }
}
