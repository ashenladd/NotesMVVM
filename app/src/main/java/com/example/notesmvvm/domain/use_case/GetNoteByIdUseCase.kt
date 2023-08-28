package com.example.notesmvvm.domain.use_case

import com.example.notesmvvm.domain.model.Note
import com.example.notesmvvm.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id:Int):Note?{
        return repository.getNoteById(id)
    }
}