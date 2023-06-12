package com.example.composenotesapp.navigation

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

@Composable
fun NotesNavigation(nList:List<Note>,onAddNote:(Note)->Unit){
        val navController= rememberNavController()

    NavHost(navController = navController, startDestination = NotesScreens.NotesScreen.name){

        composable(NotesScreens.NotesScreen.name){
            NotesScreen(nList =nList,navController)
        }

        composable(NotesScreens.NewNoteScreen.name+"/{noteId}",
        arguments = listOf(navArgument(name = "noteId"){
            type= NavType.StringType
        })
        ){
            NewNoteScreen(navController=navController,onAddNote)
        }

        composable(NotesScreens.DetailsScreen.name, arguments = listOf(navArgument(name="noteId"){
            type= NavType.StringType
        })){
            NoteDetailsScreen(navController = navController, noteId =it.arguments?.getString("noteId"),nList = nList)
        }
    }

    }