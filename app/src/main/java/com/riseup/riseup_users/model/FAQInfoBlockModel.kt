package com.riseup.riseup_users.model

class FAQInfoBlockModel {

    var title:String
    var type:String
    var info:String

    constructor(title: String, type: String, info: String) {
        this.title = title
        this.type = type
        this.info = info
    }
}