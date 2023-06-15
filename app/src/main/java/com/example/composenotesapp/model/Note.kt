package com.example.composenotesapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
data class Note(
    val uuid: UUID=UUID.randomUUID(),
    var title:String,
    var description:String,
    val entryDate: Date = Date.from(Instant.now()))

