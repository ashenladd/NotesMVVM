package com.example.notesmvvm.domain.use_case

data class NoteUseCases(
    val addNote : AddNoteUseCase,
    val deleteNote : DeleteNoteUseCase,
    val getNoteByID: GetNoteByIdUseCase,
    val getNotes :GetNotesUseCase
)

