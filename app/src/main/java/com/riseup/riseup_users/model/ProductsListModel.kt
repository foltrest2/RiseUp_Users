package com.riseup.riseup_users.model

class ProductsListModel {

    var image:String
    var name:String
    var price:Double
    var type:String

    constructor(image: String, name: String, price: Double, type:String) {
        this.image= image
        this.name = name
        this.price = price
        this.type = type
    }
}