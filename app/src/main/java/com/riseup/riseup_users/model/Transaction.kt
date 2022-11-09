package com.riseup.riseup_users.model

import java.sql.Date

data class Transaction(
    val id : String = "",
    var code : String = "",
    var date : Date? = null,
    var diamonds: Int = 0,
    val discoID : String = "",
    var method: String = "",
    var shoppingCar : ArrayList<Product>? = null,
    var state : Int = 0,
    val userID : String = ""
)