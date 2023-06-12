package com.example.composenotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenotesapp.model.NotesViewModel
import com.example.composenotesapp.navigation.NotesNavigation
import com.example.composenotesapp.screens.newNoteScreen.NewNoteScreen
import com.example.composenotesapp.screens.notesScreen.NotesScreen
import com.example.composenotesapp.ui.theme.ComposeNotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          MainApp {
              val viewModel= viewModel<NotesViewModel>()
               val noteViewModel: NotesViewModel by viewModels()
              NotesNavigation(nList = noteViewModel.loadAllNotes(), onAddNote = {
                  noteViewModel.addNote(it)
              })
//                NotesScreen(nList = noteViewModel.loadAllNotes())
//                NewNoteScreen()
          }
        }
    }
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeNotesAppTheme {
        Greeting("Android")
    }
}