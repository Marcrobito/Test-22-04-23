package com.example.test.data

import com.example.test.domain.Item
import com.squareup.moshi.Json

data class ItemDTO(
    @Json(name = "id") val id:String?,
    @Json(name = "image") val image:String?,
    @Json(name = "name") val name:String?,
    @Json(name = "price") val price:Double = 0.0,
)

fun ItemDTO.mapToItem() = Item(id, image?:"https://imgholder.ru/600x300/8493a8/adb9ca&text=IMAGE+HOLDER&font=kelson", name?:"", price)
fun List<ItemDTO>.mapToItem() = this.map { it.mapToItem() }