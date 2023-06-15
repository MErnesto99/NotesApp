package com.example.composenotesapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.room.util.appendPlaceholders
import com.example.composenotesapp.model.Note
import com.example.composenotesapp.utils.UUIDConverter
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier,
    text: String,
    placeholder:String,
    onTextChange: (String)->Unit,
    maxLines: Int,
    onImeAction:()->Unit={}
){
    val keyboardController= LocalSoftwareKeyboardController.current//This is controller of the keyboard

    TextField(value =text ,
        onValueChange = onTextChange,
        placeholder={ Text(text = placeholder)},
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }), modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title:String,
    modifier: Modifier=Modifier,
    onNavIconClick:()->Unit,
    iconAction:ImageVector,
    navIcon:ImageVector,
    onClick: () -> Unit,
    color:Color){
    TopAppBar(
        title={ Text(text = title)},
        colors=TopAppBarDefaults.smallTopAppBarColors(color),
        modifier = modifier,
        navigationIcon = {
            Icon(imageVector = navIcon,
                contentDescription ="",
                modifier = Modifier.clickable {
                    onNavIconClick()
                })
        },
        actions = {
            Icon(imageVector = iconAction,
                modifier = Modifier.clickable {onClick()},
                contentDescription ="icon")
        })
}

@Composable
fun NotesButton(
    modifier: Modifier=Modifier,
    text:String,
    onClick: () -> Unit,
    enabled: Boolean =true
){
        Button(
            onClick = { onClick.invoke() },
            modifier = modifier,
            enabled=enabled) {
            
            Text(text = text)
        } 
    
}

@Composable
fun CardNote(
    modifier: Modifier=Modifier,
    note: Note,
    onNoteClicked:(String)->Unit,
    onDeleteNote: (Note)->Unit,
    maxLines:Int
    ){
    
    Card(
        modifier = modifier
            .padding(4.dp)
            .clickable { onNoteClicked(UUIDConverter().fromUIID(note.uuid)!!)  },
        colors=CardDefaults.cardColors(Color.White),
        shape = CircleShape.copy(all= CornerSize(10.dp)),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {

            Text(text = note.title, modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth(),
                overflow= TextOverflow.Ellipsis,
                maxLines = maxLines)

            Text(text = note.description, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                overflow= TextOverflow.Ellipsis,
                maxLines = maxLines)

        Row(modifier = Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.End) {
               Text(text ="${note.entryDate}")
        }
    }
        
       

}