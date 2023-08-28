package com.example.notesmvvm.domain.use_case

import com.example.notesmvvm.domain.model.Note
import com.example.notesmvvm.domain.model.NoteException
import com.example.notesmvvm.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository,
) {

    @Throws(NoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw NoteException(message = "Title can not be empty...")
        }
        if (note.content.isBlank()) {
            throw NoteException(message = "Content can not be empty...")
        }
        repository.addNote(note)
    }
}