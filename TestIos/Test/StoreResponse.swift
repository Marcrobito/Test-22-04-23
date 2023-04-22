//
//  StoreResponse.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import Foundation

struct StoreResponse: Hashable, Codable {
    var item: StoreItem
}

struct StoreItem: Hashable, Codable {
    var props: Props
}

struct Props: Hashable, Codable {
    var initialData: InitialData
}

struct InitialData: Hashable, Codable {
    var searchResult: SearchResult
}

struct SearchResult: Hashable, Codable {
    var itemStacks: [ItemStack]
    var paginationV2:PaginationV2
}


struct ItemStack: Hashable, Codable {
    var items: [Item]
}

struct Item: Hashable, Codable {
    var name: String
    var image: String
    var price: Double
}

struct PaginationV2: Hashable, Codable {
    var maxPage: Int
}
