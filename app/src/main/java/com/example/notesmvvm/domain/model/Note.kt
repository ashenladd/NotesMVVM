package com.example.notesmvvm.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val date: String,
    @PrimaryKey val id: Int? = null
)

class NoteException(message:String):Exception(message)