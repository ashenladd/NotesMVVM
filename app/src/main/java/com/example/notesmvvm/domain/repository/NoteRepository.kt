package com.example.notesmvvm.domain.repository

import com.example.notesmvvm.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun addNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id:Int): Note?
}