package com.example.notesmvvm.ui.notes

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notesmvvm.domain.util.NoteOrderBy
import com.example.notesmvvm.ui.notes.component.NoteItem
import com.example.notesmvvm.ui.notes.component.OrderByMenu
import com.example.notesmvvm.ui.notes.component.OrderTypeMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel(),
) {
    val stateItems = viewModel.stateItems.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Add Note"
                )
            }
        },
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "My Notes",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Note"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(4.dp)
                .padding(paddingValues)
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = {
                    Log.d("Toogle Order", "is visible? ${stateItems.isOrderSectionVisible}")
                    viewModel.onEvent(NotesEvent.ToggleOrderSection)
                }) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Filter List",
                        tint = Color.Gray,
                    )
                }
                Text(
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    text = when (viewModel.stateItems.value.noteOrderBy) {
                        is NoteOrderBy.Recent -> "Recent"
                        is NoteOrderBy.Title -> "Title"
                        is NoteOrderBy.Date -> "Date"
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(12.dp),
                    color = Color.Gray,
                )
                OrderTypeMenu(noteOrderBy = stateItems.noteOrderBy,
                    onOrderChange = {
                        viewModel.onEvent(
                            NotesEvent.OnChangeNoteOrderBy(it)
                        )
                    })
            }
            AnimatedVisibility(
                visible = stateItems.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut()
            ) {
                OrderByMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    noteOrderBy = stateItems.noteOrderBy,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.OnChangeNoteOrderBy(it))
                        viewModel.onEvent(NotesEvent.ToggleOrderSection)
                    }
                )
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                columns = GridCells.Fixed(2),
            ) {
                items(stateItems.notes) { note ->
                    NoteItem(note = note,
                        modifier = Modifier.clickable { },
                        onDeleteClick = {
                            viewModel.onEvent(NotesEvent.OnDelete(note))
                        })
                }
            }
        }

    }
}