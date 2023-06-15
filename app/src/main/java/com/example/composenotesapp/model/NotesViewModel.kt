package com.example.composenotesapp.model

import androidx.lifecycle.ViewModel
import com.example.composenotesapp.data.NoteDataSource
import com.example.composenotesapp.utils.UUIDConverter


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

    fun updateNote(note: Note){
        notesList.filter {thisNote->
            UUIDConverter().fromUIID(thisNote.uuid)==UUIDConverter().fromUIID(note.uuid)
        }

        notesList.first().title=note.title
        notesList.first().description=note.description


    }

}
