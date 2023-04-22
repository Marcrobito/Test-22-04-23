//
//  CoordinatorView.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import SwiftUI

struct CoordinatorView: View {
    
    @StateObject private var coordinator = Coordinator()
    var body: some View {
        NavigationStack(path: $coordinator.path) {
            coordinator.build(page: .search)
                .navigationDestination(for: Page.self){ page in
                    coordinator.build(page: page)
                }
                .fullScreenCover(item: $coordinator.overlay){ overlay in
                    coordinator.build(overlay: overlay)
                }
        }
        .environmentObject(coordinator)
    }
}

struct CoordinatorView_Previews: PreviewProvider {
    static var previews: some View {
        CoordinatorView()
    }
}
