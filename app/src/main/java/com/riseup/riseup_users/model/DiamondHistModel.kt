package com.riseup.riseup_users.model

class DiamondHistModel {

    var discoName:String
    var transID:String
    var date:String
    var diamondsQty:Int

    constructor(discoName: String, transID: String, date: String, diamondsQty: Int) {
        this.discoName = discoName
        this.transID = transID
        this.date = date
        this.diamondsQty = diamondsQty
    }
}