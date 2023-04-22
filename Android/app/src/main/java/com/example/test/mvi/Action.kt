package com.example.test.mvi

sealed class Action {
    data class SearchAction(val searchQuery:String):Action()
}