package com.example.carpoint.screens.Dashboard.ProfileScreen

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.carpoint.R
import com.example.carpoint.sharedPreferences.SharedPreferences

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {

    // Context persists in app, after restart -> different context.
    val context = LocalContext.current
    // Retrieve sharedPreferences object.
    // If no object -> gets created.
    val profilePref = remember {
        context.getSharedPreferences(SharedPreferences.PROFILE_PICTURE_PREF.prefName,0)
    }
    // Sets the Shared Preferences key -> Current UserId
    val profileKey1 = remember { viewModel.getCurrentUserId() }
    // Get the picture Uri as a String from shared preferences, if the key is not found -> uri = null.
    val uriAsString = remember { profilePref.getString(profileKey1, null) }

    // Gets the Shared Preference Instance for Picture Uris and checks the Value for the key.
    // If not found -> null.
    var uriForImageDisplay by remember {
        mutableStateOf(
            if (uriAsString != null) {
                Uri.parse(uriAsString)
            } else {
                null
            }
        )
    }

    // Takes the permission from system -> to increase user experience after device restart. Otherwise picture won't be shown.
    val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
    uriForImageDisplay?.let { context.contentResolver.takePersistableUriPermission(it, flag) }

    // Enables to pick a photo from gallery and the uri gets added after the user selects.
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriForImageDisplay = it
            // Adds the current profile image uri as base64 string to the real time database.
            viewModel.uploadPictureToDatabase(uriForImageDisplay, context)
            // Adds the current profile image uri to shared preferences.
            with(profilePref.edit()) {
                putString(profileKey1, uriForImageDisplay.toString())
                apply()
            }
        })

    // Launches the permission request and shows a message to the user.
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                Toast.makeText(context, R.string.permissionGranted, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, R.string.permissiondenied, Toast.LENGTH_SHORT).show()
            }
        })

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape, modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        ) {
            AsyncImage(model = if (uriForImageDisplay == null) {
                R.drawable.person_placeholder
            } else {
                uriForImageDisplay!!
            }, contentDescription = "profile_image", modifier = Modifier
                .clickable {
                    if (ContextCompat.checkSelfPermission(
                            context, android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    } else {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
                .fillMaxSize())
        }
    }
}