package com.example.composenotesapp.model

import androidx.lifecycle.ViewModel
import com.example.composenotesapp.data.NoteDataSource


class NotesViewModel : ViewModel(){



    var notesList= mutableListOf<Note>()

    init{

        notesList.addAll(NoteDataSource().loadNotes())
    }

    fun loadAllNotes():List<Note>{
        return notesList
    }
    fun addNote(note:Note){
        notesList.add(note)
    }

    fun removeNote(note:Note){
        notesList.remove(note)
    }
}
