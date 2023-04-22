//
//  SearchView.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import SwiftUI

struct SearchView: View {
    
    @State private var product: String = ""
    
    @ObservedObject var viewModel = SearchViewModel()
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack{
                TextField("Search", text: $product)
                Button(action: {
                    viewModel.queryProduct(product)
                }, label: {
                    Text("Search")
                })
            }.padding()
            
            ProductsListView(items: viewModel.items)
            
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}

