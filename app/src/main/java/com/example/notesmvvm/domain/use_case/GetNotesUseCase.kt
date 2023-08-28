package com.example.notesmvvm.domain.use_case

import com.example.notesmvvm.domain.model.Note
import com.example.notesmvvm.domain.repository.NoteRepository
import com.example.notesmvvm.domain.util.NoteOrderBy
import com.example.notesmvvm.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrderBy: NoteOrderBy = NoteOrderBy.Title(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map {notes ->
            when(noteOrderBy.orderType){
                is OrderType.Descending ->{
                    when(noteOrderBy){
                        is NoteOrderBy.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrderBy.Date -> notes.sortedByDescending { it.date }
                        is NoteOrderBy.Recent -> notes.sortedByDescending { it.timeStamp }
                    }
                }
                is OrderType.Ascending ->{
                    when(noteOrderBy){
                        is NoteOrderBy.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrderBy.Date -> notes.sortedBy { it.date }
                        is NoteOrderBy.Recent -> notes.sortedBy { it.timeStamp }
                    }
                }
            }
        }
    }
}