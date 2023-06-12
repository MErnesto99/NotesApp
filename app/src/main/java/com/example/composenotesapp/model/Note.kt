package com.example.composenotesapp.model

import java.util.UUID

data class Note(
    val uuid: UUID=UUID.randomUUID(),
    var title:String,
    var description:String)
