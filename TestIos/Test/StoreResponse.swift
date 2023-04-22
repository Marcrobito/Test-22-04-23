//
//  StoreResponse.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import Foundation
import SwiftUI

struct StoreResponse: Hashable, Codable {
    var item: StoreItem
}

struct StoreItem: Hashable, Codable {
    var props: Props
}

struct Props: Hashable, Codable {
    var pageProps: PageProps
}

struct PageProps: Hashable, Codable {
    var initialData: InitialData
}

struct InitialData: Hashable, Codable {
    var searchResult: SearchResult
}

struct SearchResult: Hashable, Codable {
    var title: String
    var itemStacks: [ItemStack]
    var paginationV2:PaginationV2
}


struct ItemStack: Hashable, Codable {
    var items: [Item]
}

struct Item: Hashable, Codable, Identifiable {
    var id:String?
    var name: String?
    private var image: String?
    private var price: Double?
    var priceFormated : String{
        String(format: "$%.2f", price ?? 0.0)
    }
    var imageView: Image {
        Image(uiImage: image?.loadImage() ?? "https://imgholder.ru/600x300/8493a8/adb9ca&text=IMAGE+HOLDER&font=kelson".loadImage())
    }
}

struct PaginationV2: Hashable, Codable {
    var maxPage: Int
}
