package com.riseup.riseup_users.model

import java.util.*

class CardLastPaymentsModel {

    var id:String
    var discoName:String
    var cardInfo:String
    var amount:Double
    var date:String

    constructor(){
        id = UUID.randomUUID().toString()
        this.discoName = "No disco name"
        this.cardInfo = "no card info"
        this.amount = 0.0
        this.date = "10/11/22"
    }

    constructor(discoName: String, cardInfo: String, amount: Double, date: String) {
        this.id = UUID.randomUUID().toString()
        this.discoName = discoName
        this.cardInfo = cardInfo
        this.amount = amount
        this.date = date
    }


    constructor(id: String, discoName: String, cardInfo: String, amount: Double, date: String) {
        this.id = id
        this.discoName = discoName
        this.cardInfo = cardInfo
        this.amount = amount
        this.date = date
    }
}