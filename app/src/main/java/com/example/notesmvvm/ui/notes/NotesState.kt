package com.example.notesmvvm.ui.notes

import com.example.notesmvvm.domain.model.Note
import com.example.notesmvvm.domain.util.NoteOrderBy
import com.example.notesmvvm.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrderBy: NoteOrderBy = NoteOrderBy.Recent(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val isLoading: Boolean = true,
)