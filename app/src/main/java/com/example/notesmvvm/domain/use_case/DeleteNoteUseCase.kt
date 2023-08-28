package com.example.notesmvvm.domain.use_case

import com.example.notesmvvm.domain.model.Note
import com.example.notesmvvm.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}