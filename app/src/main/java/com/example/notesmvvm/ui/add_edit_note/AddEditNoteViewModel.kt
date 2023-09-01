package com.example.notesmvvm.ui.add_edit_note

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesmvvm.domain.model.Note
import com.example.notesmvvm.domain.model.NoteException
import com.example.notesmvvm.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private var _noteTitleState = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter Title.."
        )
    )
    val noteTitleState = _noteTitleState

    private var _noteContentState = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter Content.."
        )
    )
    val noteContentState = _noteContentState

    private var currentId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            Log.d("AddEditScreen","$noteId")
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNoteByID(noteId)?.also { note ->
                        currentId = note.id
                        _noteTitleState.value = _noteTitleState.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContentState.value = _noteContentState.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )

                    }
                }

            }

        }

    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = _noteTitleState.value.text,
                                content = _noteContentState.value.text,
                                timeStamp = System.currentTimeMillis(),
                                date = LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                id = currentId
                            )
                        )
                    }catch (e: NoteException){
                        Log.d("SaveNoteError","Error Msg = ${e.message}")
                    }

                }
            }

            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    text = event.title
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContentState.value = noteContentState.value.copy(
                    text = event.content
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitleState.value = _noteTitleState.value.copy(
                    isHintVisible = !event.focusState.isFocused && _noteTitleState.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContentState.value = noteContentState.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContentState.value.text.isBlank()
                )
            }
        }
    }
}