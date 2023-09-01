package com.example.notesmvvm.ui.add_edit_note.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TransparentTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    cursorBrush:Brush = SolidColor(Color.White)
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) },
            textStyle = textStyle,
            singleLine = singleLine,
            cursorBrush = cursorBrush
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun previewTransparentTextField(){
    TransparentTextField(
        text = "Something",
        hint = "Write..",
        onValueChange = {},
        onFocusChange ={},
        isHintVisible = false,
    )
}