package com.example.notesmvvm.ui.util

sealed class Screen (val route:String){
    object NoteScreen:Screen(route = "notes_screen")
}