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
            
            if !viewModel.queries.isEmpty {
                ScrollView(.horizontal){
                    HStack(spacing: 20) {
                        ForEach(viewModel.queries, id:\.self) { query in
                            Button(action: {
                                viewModel.queryProduct(query.query)
                            }, label: {
                                Text(query.query)
                            })
                        }
                    }
                }
            }
            
            List(viewModel.items){ item in
                ItemRow(item: item).onAppear(){
                    if viewModel.items.last == item {
                        viewModel.getNextPage(product)
                    }
                }
            }
            
            
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}

