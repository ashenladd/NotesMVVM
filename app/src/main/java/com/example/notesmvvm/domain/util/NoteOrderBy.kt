package com.example.notesmvvm.domain.util

sealed class NoteOrderBy(val orderType: OrderType){
    data class Title(val titleType: OrderType):NoteOrderBy(titleType)
    data class Date(val dateType: OrderType):NoteOrderBy(dateType)
    data class Recent(val recentType: OrderType):NoteOrderBy(recentType)

    fun rewriteOrderType(orderType: OrderType):NoteOrderBy{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Recent -> Recent(orderType)
        }
    }
}
