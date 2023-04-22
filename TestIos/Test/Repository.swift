//
//  Repository.swift
//  Test
//
//  Created by Marco Antonio Martinez Gutierrez on 22/04/23.
//

import Foundation
import Alamofire


class Repository {
    private let BASE_URL = "https://00672285.us-south.apigw.appdomain.cloud/demo-gapsi/"
    private let headers: HTTPHeaders = ["X-IBM-Client-Id":"adb8204d-d574-4394-8c1a-53226a40876e"]
    private var currentPage = 1
    private var maxPages:Int? = nil
    private var currentQuery:String = ""
    
    
    
    func fetchProduct(product: String) {
        let request = AF.request("\( BASE_URL)search?&query=\(product)&page=\(currentPage)", headers: headers)
        request.validate().responseJSON { response in
            switch response.result {
            case .success:
                do{
                    let result = try JSONDecoder().decode(StoreResponse.self, from: response.data!)
                    
                }catch{
                    
                }
            case .failure(let error):
                print(error)
                
            }
        }
    }
}
