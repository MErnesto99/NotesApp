package com.example.composenotesapp.screens.notesScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.composenotesapp.components.AppTopBar
import com.example.composenotesapp.components.CardNote
import com.example.composenotesapp.model.Note
import com.example.composenotesapp.navigation.NotesScreens
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun NotesScreen(notesList:List<Note>,navController: NavController,onDelete:(Note)->Unit){

    val context = LocalContext.current

    val notes = remember { mutableStateListOf(*notesList.toTypedArray()) }
    val archive=SwipeAction(
        onSwipe = {
            Log.d("Swipe","Swipped")
        },
        icon = {
            Icon(imageVector = Icons.Default.Edit, contentDescription ="Archive")
        },
        background = Color.Blue
    )

    val delete=SwipeAction(
        onSwipe = {

        },
        icon = {
            Icon(imageVector = Icons.Default.Delete, contentDescription ="Archive")
        },
        background = Color.Red
    )

    Surface() {
        Column() {
            AppTopBar(title = "Notes",
                navIcon = Icons.Default.Home,
                onNavIconClick = {},
                iconAction = Icons.Default.Add,
                onClick = {

                   navController.navigate(NotesScreens.NewNoteScreen.name)
                }, color = Color.Yellow)

            Column() {

                LazyColumn(){
                    items(
                        items = notesList,
                    key = {listItem->
                            listItem.uuid
                    }){note->

                        SwipeableActionsBox(
                            swipeThreshold=200.dp,
                            startActions = listOf(archive),
                            endActions = listOf(delete.copy(onSwipe = {
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                                onDelete(note)
                                notes.remove(note)
                            }))
                        ) {
                            CardNote(note = note, onNoteClicked ={noteId->
                                navController.navigate(NotesScreens.DetailsScreen.name+"/$noteId")
                            }, onDeleteNote = {
                                onDelete(note)
                                notes.remove(note)

                            } , maxLines = 1)


                        }
                    }
                }

            }

        }
    }

}