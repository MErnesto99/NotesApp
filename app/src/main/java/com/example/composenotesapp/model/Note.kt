package com.example.composenotesapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID
@Entity(tableName = "notes_tbl")
@RequiresApi(Build.VERSION_CODES.O)
data class Note(
    @PrimaryKey
   val uuid: UUID=UUID.randomUUID(),
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name ="description")
    var description:String,
    @ColumnInfo(name ="note_entry")
    val entryDate: Date = Date.from(Instant.now()))

