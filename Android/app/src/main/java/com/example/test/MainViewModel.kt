package com.example.test

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eigthteentech.gapsi.entities.DB
import com.eigthteentech.gapsi.entities.QueryEntity
import com.example.test.data.ProductDataSource
import com.example.test.domain.Response
import com.example.test.mvi.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSource: ProductDataSource,
    private val database: DB
) : ViewModel() {
    private val _state = MutableStateFlow(State.MainState())
    val state: StateFlow<State.MainState> get() = _state
    private var currentItem: Long = 1

    init {
        val queries = database.itemDAO.getItems()
        _state.value = _state.value.copy(queries = queries)
        if (queries.isNotEmpty()) {
            currentItem = (queries.lastIndex + 2).toLong()
        }
    }

    fun search(searchQuery: String) {
        _state.value = _state.value.copy(isLoading = true, searchQuery = searchQuery)
        if (database.itemDAO.getItem(searchQuery).isEmpty()) {
            database.itemDAO.insert(QueryEntity(currentItem,searchQuery))
            _state.value =  _state.value.copy(queries = database.itemDAO.getItems())
        }
        currentItem++
        viewModelScope.launch {
            val result = dataSource.getProductList(searchQuery)
            if (result is Response.Success) {
                _state.value =
                    _state.value.copy(
                        isLoading = false,
                        data = result.data,
                        searchQuery = searchQuery,
                        error = null
                    )
            }
            if (result is Response.Error) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.error.message ?: "Some Error"
                )
            }
        }
    }

    fun getNext() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            _state.value.searchQuery?.let {
                val result = dataSource.getProductList(it)
                if (result is Response.Success) {
                    println(result.data)
                    _state.value =
                        _state.value.copy(isLoading = false, data = _state.value.data + result.data)
                }
                if (result is Response.Error) {
                    _state.value = _state.value.copy(isLoading = false)
                }

            }

        }
    }
}