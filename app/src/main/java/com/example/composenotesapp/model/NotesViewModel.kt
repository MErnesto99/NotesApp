package com.example.composenotesapp.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenotesapp.data.NoteDataSource
import com.example.composenotesapp.notesRepository.NotesRepository
import com.example.composenotesapp.utils.UUIDConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel(){
//   private var notesList= mutableListOf<Note>()

    private val _notesList=MutableStateFlow<List<Note>>(emptyList())

    var notesList = _notesList.asStateFlow()

    init{
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllNotes().distinctUntilChanged().collect{listNotes->
                if(listNotes.isNullOrEmpty()){
                    Log.d("Empty", ": Empty List")

                    //Nothing
                }else{
                    _notesList.value=listNotes

                }
            }
        }
    }

    fun addNote(note:Note)=viewModelScope.launch{
       repository.addNote(note = note)
    }

    fun removeNote(note:Note)=viewModelScope.launch{
        repository.deleteNote(note = note)
    }

    fun updateNote(note: Note)=viewModelScope.launch{

        repository.updateNote(note = note)

//        notesList.filter {thisNote->
//            UUIDConverter().fromUIID(thisNote.id)==UUIDConverter().fromUIID(note.id)
//        }
//        notesList.first().title=note.title
//        notesList.first().description=note.description

    }

    fun getAllNotes()= viewModelScope.launch {
        repository.getAllNotes()
    }
//

}
