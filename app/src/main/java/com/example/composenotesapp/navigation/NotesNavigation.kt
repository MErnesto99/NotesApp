package com.example.composenotesapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composenotesapp.model.Note
import com.example.composenotesapp.screens.newNoteScreen.NewNoteScreen
import com.example.composenotesapp.screens.noteDetailsScreen.NoteDetailsScreen
import com.example.composenotesapp.screens.notesScreen.NotesScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesNavigation(nList:List<Note>,onAddNote:(Note)->Unit,onUpdateNote:(Note)->Unit,onDelete:(Note)->Unit){
        val navController= rememberNavController()

    NavHost(navController = navController, startDestination = NotesScreens.NotesScreen.name){

        composable(NotesScreens.NotesScreen.name){
            NotesScreen(notesList =nList,navController,onDelete)
        }

        composable(NotesScreens.NewNoteScreen.name){
            NewNoteScreen(navController=navController,onAddNote)
        }

        composable(NotesScreens.DetailsScreen.name + "/{noteId}", arguments = listOf(navArgument(name="noteId"){
            type= NavType.StringType
        })){
            NoteDetailsScreen(
                navController = navController,
                it.arguments?.getString("noteId"),
                nList = nList,
                onEditNote =onUpdateNote )
        }
    }

    }