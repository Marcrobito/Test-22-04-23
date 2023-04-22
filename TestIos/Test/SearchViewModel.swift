//
//  SearchViewModel.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import Foundation
import RealmSwift

class SearchViewModel: ObservableObject {
    
    let repository = Repository()
    
    @Published var items: [Item] = []
    
    func queryProduct(_ product:String){
        repository.fetchProduct(product: product) { result in
            if result == nil{
                return
            }
            self.items = result!
        }
    }
    
    func getNextPage(_ product:String){
        repository.fetchProduct(product: product) { result in
            if result == nil{
                return
            }
            self.items += result!
        }
    }
    
    
}
