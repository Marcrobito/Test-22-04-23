//
//  Extensions.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import Foundation
import SwiftUI

extension String {
    func loadImage() -> UIImage {
        do {
            guard let url = URL(string: self) else {
                return UIImage()
            }
            let data:Data = try Data(contentsOf: url)
            return UIImage(data: data) ?? UIImage()
        } catch {
            
        }
        return UIImage()
    }
}
