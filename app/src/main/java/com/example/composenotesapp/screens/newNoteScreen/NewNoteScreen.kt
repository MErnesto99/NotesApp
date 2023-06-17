package com.example.composenotesapp.screens.newNoteScreen

import android.os.Build
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.composenotesapp.components.AppTopBar
import com.example.composenotesapp.components.NoteInputText
import com.example.composenotesapp.model.Note
import com.example.composenotesapp.model.NotesViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewNoteScreen(
    navController: NavController,
    onAddNote:(Note)->Unit){

    var line=1
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var maxLines by remember {
       mutableStateOf(true)
    }



    Surface(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier.fillMaxSize()){
            AppTopBar(title ="New Note",
                iconAction = Icons.Default.Check,
                onNavIconClick = {
                     navController.popBackStack()
                },
                navIcon =Icons.Rounded.ArrowBack,
                onClick = {
                    navController.popBackStack()
                    onAddNote(Note(title=title, description = description))
                    title=""
                    description=""

                          },
                color = Color.Yellow)

            Column() {
                NoteInputText(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    onTextChange = {
                        title=it
                    },
                    placeholder = "Title",
                    maxLines = 2
                )
                NoteInputText(
                    modifier = Modifier.fillMaxSize(),
                    text = description,
                    onTextChange = {
                        description=it
                    },
                    placeholder="Description",
                    maxLines = Int.MAX_VALUE
                )


            }


        }
    }
}