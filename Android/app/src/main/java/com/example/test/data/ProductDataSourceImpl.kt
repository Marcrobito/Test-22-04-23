package com.example.test.data

import com.example.test.domain.Item
import com.example.test.domain.Response
import com.example.test.network.Api
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(private val api: Api) : ProductDataSource {

    private var currentPage = 1
    private var maxPage = -1
    private var currentQuery = ""

    override suspend fun getProductList(searchQuery: String, page: Int): Response<List<Item>> {
        return try {
            if (currentQuery == searchQuery && currentPage <= maxPage) {
                currentPage++
            }

            if (currentQuery != searchQuery) {
                currentPage = 1
                maxPage = -1
            }

            if (maxPage > 0 && currentPage == maxPage)
                Response.Success(emptyList<Item>())

            val result = api.getProductList(searchQuery, currentPage)

            currentQuery = searchQuery

            if (result.item.props.pageProps.initialData.searchResult.itemStacks[0].items.isEmpty()) {
                Response.Error(Exception("Didn't Found"))
            }

            maxPage = result.item.props.pageProps.initialData.searchResult.paginationV2.maxPage
            Response.Success(result.item.props.pageProps.initialData.searchResult.itemStacks[0].items.mapToItem())

        } catch (e: Exception) {
            Response.Error(e)
        }

    }
}