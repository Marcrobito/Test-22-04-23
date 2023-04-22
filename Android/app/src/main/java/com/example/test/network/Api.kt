package com.example.test.network

import com.example.test.data.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("search")
    suspend fun getProductList(
        @Query("query") searchCriteria: String,
        @Query("page") page: Int = 1
    ): ProductsResponse
}