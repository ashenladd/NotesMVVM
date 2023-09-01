package com.example.notesmvvm.ui.add_edit_note

import androidx.compose.ui.focus.FocusState
import com.example.notesmvvm.domain.model.Note

sealed class AddEditNoteEvent {
    object SaveNote : AddEditNoteEvent()
    data class EnteredTitle(val title:String):AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState):AddEditNoteEvent()
    data class  EnteredContent(val content:String):AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState):AddEditNoteEvent()

}