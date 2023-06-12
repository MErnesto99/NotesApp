package com.example.composenotesapp.navigation

enum class NotesScreens {
    NotesScreen,
    DetailsScreen,
    NewNoteScreen;


    companion object{
        fun fromRoute(route:String):NotesScreens
        = when(route?.substringBefore("/")){
            NotesScreen.name -> NotesScreen
            DetailsScreen.name -> DetailsScreen
            NewNoteScreen.name -> NewNoteScreen
            null -> NotesScreen
            else -> throw IllegalArgumentException("Something wen wrong")

        }
    }
}