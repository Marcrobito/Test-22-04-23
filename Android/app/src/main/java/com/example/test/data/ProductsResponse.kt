package com.example.test.data

import com.squareup.moshi.Json

data class ProductsResponse(
    @Json(name = "responseStatus") val responseStatus: String,
    @Json(name = "item") val item: ItemResponse,
)


data class ItemResponse(
    @Json(name = "props") val props: Props,
)

data class Props(
    @Json(name = "pageProps") val pageProps: PageProps,
)

data class PageProps(
    @Json(name = "initialData") val initialData: InitialData,
)

data class InitialData(
    @Json(name = "searchResult") val searchResult: SearchResult,
)

data class SearchResult(
    @Json(name = "title") val title: String,
    @Json(name = "itemStacks") val itemStacks:List<ItemStack>,
    @Json(name = "paginationV2") val paginationV2:PaginationV2
)

data class ItemStacks(
    @Json(name = "items") val items: List<ItemDTO>,
)


data class ItemStack(
    @Json(name = "items") val items: List<ItemDTO>,
)
data class PaginationV2(
    @Json(name = "maxPage") val maxPage:Int,
    @Json(name = "pageProperties") val pageProperties: PageProperties,
)

data class PageProperties(
    @Json(name = "page") val page:Int
)