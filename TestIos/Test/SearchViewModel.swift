//
//  SearchViewModel.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import Foundation
import RealmSwift

class SearchViewModel: ObservableObject {
    
    let realm = try! Realm()
    
    let repository = Repository()
    
    @Published var queries: [Query]
    
    @Published var items: [Item] = []
    
    init(){
        queries = Array(realm.objects(Query.self))
    }
    
    func queryProduct(_ item:String){
        var exists =  realm.objects(Query.self).filter("query == '\(item)'").first
        if exists == nil {
            let query = Query()
            query.query = item
            try! realm.write {
                realm.add(query)
            }
            queries = Array(realm.objects(Query.self))
        }
        
        repository.fetchProduct(product: item) { result in
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
