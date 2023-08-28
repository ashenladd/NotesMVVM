package com.example.notesmvvm.ui.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesmvvm.domain.model.Note
import com.example.notesmvvm.domain.use_case.NoteUseCases
import com.example.notesmvvm.domain.util.NoteOrderBy
import com.example.notesmvvm.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
):ViewModel() {
    private var _stateItems = mutableStateOf(NotesState())
    val stateItems = _stateItems

    private var recentlyDeletedNote:Note? = null

    init {
        getNotes(NoteOrderBy.Recent(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.OnChangeNoteOrderBy ->{
                if(stateItems.value.noteOrderBy==event.noteOrderBy){
                    return
                }
                getNotes(event.noteOrderBy)
            }
            is NotesEvent.OnDelete ->{
                viewModelScope.launch {
                    recentlyDeletedNote = event.note
                    noteUseCases.deleteNote(event.note)
                }
            }
            is NotesEvent.OnRestore ->{
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote?:return@launch)
                    recentlyDeletedNote = null
                }
            }
        }
    }

    private fun getNotes(noteOrderBy:NoteOrderBy){
        viewModelScope.launch {
            noteUseCases.getNotes(noteOrderBy).collectLatest {notes->
                stateItems.value =stateItems.value.copy(
                    notes = notes,
                    noteOrderBy = noteOrderBy
                )
            }
        }
    }
}