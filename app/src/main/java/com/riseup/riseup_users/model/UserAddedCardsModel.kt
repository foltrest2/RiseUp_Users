package com.riseup.riseup_users.model

class UserAddedCardsModel {
    var holder:String
    var date:String
    var cardNumber:String
    var cvv:String

    constructor(holder: String, date: String, cardNumber: String, cvv:String) {
        this.holder= holder
        this.date = date
        this.cardNumber = cardNumber
        this.cvv = cvv
    }
}