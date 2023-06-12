package com.example.composenotesapp.screens.notesScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.composenotesapp.components.AppTopBar
import com.example.composenotesapp.components.CardNote
import com.example.composenotesapp.model.Note
import com.example.composenotesapp.navigation.NotesScreens
import com.example.composenotesapp.utils.UUIDConverter


@Composable
fun NotesScreen(nList:List<Note>,navController: NavController){

    val context = LocalContext.current

    Surface() {
        Column() {
            AppTopBar(title = "Notes",
                navIcon = Icons.Default.Home,
                iconAction = Icons.Default.Add,
                onClick = {
                   navController.navigate(NotesScreens.NewNoteScreen.name)
                }, color = Color.Yellow)

            Column() {
                LazyColumn(){
                    items(items = nList){note->
                        CardNote(note = note, onNoteClicked ={
                                navController.navigate(NotesScreens.NewNoteScreen.name+"/$it")
                        } , maxLines = 1)
                    }
                }
            }
        }
    }





}