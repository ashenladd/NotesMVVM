package com.example.notesmvvm.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
