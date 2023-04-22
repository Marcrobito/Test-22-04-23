package com.example.test.data

import com.example.test.domain.Item
import com.example.test.domain.Response

interface ProductRepositoryInterface {
    suspend fun getProductList(searchCriteria: String, page: Int = 1): Response<Item>
}