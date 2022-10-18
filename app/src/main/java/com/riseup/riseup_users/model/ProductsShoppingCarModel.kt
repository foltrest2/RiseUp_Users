package com.riseup.riseup_users.model

class ProductsShoppingCarModel {

    var image:String
    var name:String
    var price:Double
    var quantity:Int

    constructor(image: String, name: String, price: Double, quantity: Int) {
        this.image= image
        this.name = name
        this.price = price
        this.quantity = quantity
    }
}