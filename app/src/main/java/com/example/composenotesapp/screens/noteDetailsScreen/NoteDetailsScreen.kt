package com.example.composenotesapp.screens.noteDetailsScreen

import android.view.Window
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
import androidx.navigation.NavController
import com.example.composenotesapp.components.AppTopBar
import com.example.composenotesapp.components.NoteInputText
import com.example.composenotesapp.model.Note
import com.example.composenotesapp.utils.UUIDConverter


@Composable
fun NoteDetailsScreen(
    navController: NavController,
    noteId:String?,
   nList:List<Note>){

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

    var list=nList.filter {
        UUIDConverter().fromUIID(it.uuid)==noteId
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier.fillMaxSize()){
            AppTopBar(title ="New Note",
                iconAction = Icons.Default.Check,
                navIcon =Icons.Rounded.ArrowBack,
                onClick = {
                    navController.popBackStack()
//                    onAddNote(Note(title=title, description = description))
                },
                color = Color.Yellow)

            Column() {
                NoteInputText(
                    modifier = Modifier.fillMaxWidth(),
                    text = list.first().title,
                    onTextChange = {
                        list.first().title=it
                    },
                    placeholder = "Title",
                    maxLines = 2
                )
                NoteInputText(
                    modifier = Modifier.fillMaxSize(),
                    text = list.first().description,
                    onTextChange = {
                        list.first().description=it
                    },
                    placeholder="Description",
                    maxLines = Int.MAX_VALUE
                )
            }


        }
    }
}