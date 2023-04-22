//
//  SearchViewModel.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import Foundation
import RealmSwift

class SearchViewModel: ObservableObject {
    
    @Published var items: [Item] = []
    
    func queryProduct(_ product:String){
        
    }
    
    
}
