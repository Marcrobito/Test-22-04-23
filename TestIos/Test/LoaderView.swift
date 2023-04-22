//
//  LoaderView.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import SwiftUI

struct LoaderView: View {
    var body: some View {
        Text("Loading").background(Color.black.opacity(0.6))
    }
}

struct LoaderView_Previews: PreviewProvider {
    static var previews: some View {
        LoaderView()
    }
}
