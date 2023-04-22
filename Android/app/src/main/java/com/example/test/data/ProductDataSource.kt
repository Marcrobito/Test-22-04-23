package com.example.test.data

import com.example.test.domain.Item
import com.example.test.domain.Response

interface ProductDataSource {
    suspend fun getProductList(searchQuery: String, page: Int = 1): Response<List<Item>>
}