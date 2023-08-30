package com.example.notesmvvm.ui.notes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesmvvm.domain.util.NoteOrderBy
import com.example.notesmvvm.domain.util.OrderType

@Composable
fun OrderByMenu(
    modifier: Modifier = Modifier,
    noteOrderBy: NoteOrderBy,
    onOrderChange: (NoteOrderBy) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .background(MaterialTheme.colorScheme.primaryContainer,
                RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
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
                onSelect = { onOrderChange(NoteOrderBy.Date(noteOrderBy.orderType)) }
            )
        }
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