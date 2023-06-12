package com.example.composenotesapp.data

import com.example.composenotesapp.model.Note


class NoteDataSource(){
    fun loadNotes():List<Note>{

        return listOf(
            Note(title="Note1", description ="My First Ever note, check it out"),
            Note(title = "Movie Day", description = "Watch movies, series"),
            Note(title = "Compose", description = "Study Android compose"),
            Note(title = "Movie Day", description = "Watch movies, series"),
            Note(title = "Compose", description = "Study Android compose"),
            Note(title = "Movie Day", description = "Watch movies, series"),
            Note(title = "Compose", description = "Study Android compose Study Android composeStudy Android compose"),
            Note(title = "Movie Day", description = "Watch movies, series"),
            Note(title = "Compose", description = "Study Android compose"),
            Note(title = "Movie Day", description = "Watch movies, series"),
            Note(title = "Compose", description = "Study Android compose"),
            Note(title = "Movie Day", description = "Watch movies, series"),
            Note(title = "Movie Day", description = "Watch movies, series"),
            Note(title = "Movie Day", description = "Watch movies, series"),
        )
    }
}
