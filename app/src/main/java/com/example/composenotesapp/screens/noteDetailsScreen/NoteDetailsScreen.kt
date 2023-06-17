package com.example.composenotesapp.screens.noteDetailsScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.ArrowBack
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
import com.example.composenotesapp.utils.UUIDConverter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteDetailsScreen(
    navController: NavController,
    noteId:String?,
    onEditNote:(Note)->Unit,
   nList:List<Note>){

    var list=nList.filter {note->
        UUIDConverter().fromUIID(note.uuid)==noteId
    }

    var title by remember {
        mutableStateOf(list.first().title)
    }
    var description by remember {
        mutableStateOf( list.first().description)
    }
//    var maxLines by remember {
//        mutableStateOf(true)
//    }


    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier.fillMaxSize()){
            AppTopBar(title =list.first().title,
                iconAction = Icons.Default.Check,
                onNavIconClick = {
                    navController.popBackStack()

                },
                navIcon =Icons.Rounded.ArrowBack,
                onClick = {
                   var note=Note(title = title, description = description)
                    onEditNote(note)
                    navController.popBackStack()
//                    Toast.makeText(context, "Edit Clicked", Toast.LENGTH_SHORT).show()
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
                    text =description,
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