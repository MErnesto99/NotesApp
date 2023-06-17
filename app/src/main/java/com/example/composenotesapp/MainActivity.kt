package com.example.composenotesapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenotesapp.model.Note
import com.example.composenotesapp.model.NotesViewModel
import com.example.composenotesapp.navigation.NotesNavigation
import com.example.composenotesapp.screens.newNoteScreen.NewNoteScreen
import com.example.composenotesapp.screens.notesScreen.NotesScreen
import com.example.composenotesapp.ui.theme.ComposeNotesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_HiltWrapper_HiltViewModelFactory_ViewModelModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
          MainApp {
               val noteViewModel: NotesViewModel by viewModels()

              NotesApp(noteViewModel)

          }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesApp(notesViewModel: NotesViewModel){


    val notesList = notesViewModel.notesList.collectAsState().value



    NotesNavigation(nList = notesList, onAddNote = {
        notesViewModel.addNote(it)
    }, onUpdateNote = {
        notesViewModel.updateNote(it)
    }, onDelete = {
        notesViewModel.removeNote(it)
    })

}

@Composable
fun MainApp(content: @Composable () -> Unit){


    ComposeNotesAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

                    content()


        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    ComposeNotesAppTheme {
         MainApp {
//               val noteViewModel: NotesViewModel by viewModels()
//
//              NotesApp(noteViewModel)

          }

    }
}