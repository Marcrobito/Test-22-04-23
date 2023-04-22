//
//  Coordinator.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import SwiftUI


enum Overlay: String, Identifiable {
    case loader
    
    var id: String {
        self.rawValue
    }
}

enum Page:String, Identifiable {
    case search
    
    var id: String {
        self.rawValue
    }
}


class Coordinator: ObservableObject {
    
    @Published var path: NavigationPath = NavigationPath()
    @Published var page: Page?
    @Published var overlay: Overlay?
    
    func push(_ page: Page){
        path.push(page)
    }
    
    func present(_ overlay:Overlay){
        self.overlay = overlay
    }
    
    func pop(){
        path.remove()
    }
    
    func dismissOverLay(){
        self.overlay = nil
    }
    
    @ViewBuilder
    func build(page: Page) -> some View {
        switch page {
        case .search:
                SearchView()
        }
    }
    
    @ViewBuilder
    func build(overlay: Overlay)-> some View {
        switch overlay {
            case .loader:
            LoaderView()
        }
        
    }
}
