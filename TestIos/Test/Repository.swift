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
    private var maxPages:Int = -1
    private var currentQuery:String = ""
    
    
    
    func fetchProduct(product: String, completition: @escaping ([Item]?)-> Void) {
        if self.currentPage < self.maxPages && product == self.currentQuery{
            currentPage += 1
        }
        
        self.currentQuery = product
        
        let request = AF.request("\( BASE_URL)search?&query=\(product)&page=\(currentPage)", headers: headers)
        request.validate().responseJSON { response in
            print("response")
            switch response.result {
            case .success:
                do {
                    print("do")
                    let result = try JSONDecoder().decode(StoreResponse.self, from: response.data!)
                    print(result)
                    self.maxPages = result.item.props.pageProps.initialData.searchResult.paginationV2.maxPage
                    completition(result.item.props.pageProps.initialData.searchResult.itemStacks[0].items)
                }catch {
                    print("catch")
                    print(error)
                    completition(nil)
                }
            case .failure(let error):
                print(error)
                completition(nil)
            }
        }
    }
    
    /*func fetchProduct(for product: String) async -> [Item]? {
        let request = AF.request("\( BASE_URL)search?&query=\(product)&page=\(currentPage)", headers: headers)
        await request.validate().responseJSON { response in
            switch response.result {
            case .success:
                do{
                    let result = try JSONDecoder().decode(StoreResponse.self, from: response.data!)
                    self.maxPages = result.item.props.initialData.searchResult.paginationV2.maxPage
                     return result.item.props.initialData.searchResult.itemStacks[0]
                }catch{
                     return nil
                }
            case .failure(let error):
                print(error)
                 return nil
            }
        }
    }*/
}
