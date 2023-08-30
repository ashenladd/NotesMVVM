package com.example.notesmvvm.ui.notes.component

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesmvvm.domain.util.NoteOrderBy
import com.example.notesmvvm.domain.util.OrderType

@Composable
fun OrderTypeMenu(
    modifier: Modifier = Modifier,
    noteOrderBy: NoteOrderBy,
    onOrderChange: (NoteOrderBy) -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = {
            Log.d("OrderTypeMenu","OrderType:${noteOrderBy.orderType}, NoteOrderBy $noteOrderBy")
            onOrderChange(
                noteOrderBy.rewriteOrderType(
                    when (noteOrderBy.orderType) {
                        is OrderType.Ascending -> OrderType.Descending
                        is OrderType.Descending -> OrderType.Ascending
                    }
                )
            )
        }
    ) {
        when (noteOrderBy.orderType) {
            is OrderType.Ascending ->
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Ascending Note",
                    tint = Color.Gray,
                )

            is OrderType.Descending ->
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Descending Note",
                    tint = Color.Gray,
                )
        }
    }
}
