package com.example.notesmvvm.ui.notes.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesmvvm.domain.util.NoteOrderBy
import com.example.notesmvvm.domain.util.OrderType

@Composable
fun OrderByMenu(
    modifier: Modifier = Modifier,
    noteOrderBy: NoteOrderBy,
    onOrderChange: (NoteOrderBy) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        CustomRadioButton(
            text = "Title",
            selected = noteOrderBy is NoteOrderBy.Title,
            onSelect = { onOrderChange(NoteOrderBy.Title(noteOrderBy.orderType)) }
        )
        CustomRadioButton(
            text = "Recent",
            selected = noteOrderBy is NoteOrderBy.Recent,
            onSelect = { onOrderChange(NoteOrderBy.Recent(noteOrderBy.orderType)) }
        )
        CustomRadioButton(
            text = "Date",
            selected = noteOrderBy is NoteOrderBy.Date,
            onSelect = { onOrderChange(NoteOrderBy.Date (noteOrderBy.orderType)) }
        )
    }
}

@Preview
@Composable
fun PreviewOrderMenu() {
    OrderByMenu(
        noteOrderBy = NoteOrderBy.Recent(OrderType.Descending),
        onOrderChange = {}
    )
}