package com.example.composenotesapp.notesRepository

import com.example.composenotesapp.data.NoteDatabaseDao
import com.example.composenotesapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotesRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {


   suspend fun addNote(note: Note)
   = noteDatabaseDao.insertNote(note = note)

    suspend fun updateNote(note: Note)
    =noteDatabaseDao.updateNote(note = note)

    suspend fun deleteNote(note: Note)
    =noteDatabaseDao.deleteNote(note = note)

    suspend fun deleteAll()
    =noteDatabaseDao.deleteAll()

    suspend fun getNoteById(id:String)
    =noteDatabaseDao.getNoteById(uuid = id)

    fun getAllNotes():Flow<List<Note>> =noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()


}