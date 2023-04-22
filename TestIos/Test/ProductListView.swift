//
//  ProductListView.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import SwiftUI

struct ProductsListView: View {
    var items:[Item]
    var body: some View {
        List(items){ item in
            ItemRow(item: item)
        }
    }
}

struct ItemRow: View {
    var item:Item
    var body: some View {
        HStack {
            item.imageView
                .resizable()
                .frame(width: 60, height: 60)
            Text(item.name ?? "")
                .lineLimit(2)
                .padding(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
            Text(item.priceFormated)
                .foregroundColor(.green)
                .multilineTextAlignment(.trailing)
        }
        
    }
}

