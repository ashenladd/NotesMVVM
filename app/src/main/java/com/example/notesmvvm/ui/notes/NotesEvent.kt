package com.example.notesmvvm.ui.notes

import com.example.notesmvvm.domain.model.Note
import com.example.notesmvvm.domain.util.NoteOrderBy

sealed class NotesEvent{
    data class OnChangeNoteOrderBy(val noteOrderBy: NoteOrderBy):NotesEvent()
    data class OnDelete(val note: Note):NotesEvent()
    object OnRestore :NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
