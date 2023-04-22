package com.example.test.mvi

import com.eigthteentech.gapsi.entities.QueryEntity
import com.example.test.domain.Item

sealed class State {
    data class MainState(
        val data: List<Item> = emptyList(),
        val queries: List<QueryEntity> = emptyList(),
        val isLoading:Boolean = false,
        val searchQuery: String? = null,
        val error: String? = null
    )
}