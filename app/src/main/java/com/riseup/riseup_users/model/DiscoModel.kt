package com.riseup.riseup_users.model

data class DiscoModel (
    val id:String = "",
    var name:String = "",
    var bannerID:String = "",
    var bannerRef:String = "",
    var bannerCardID:String = "",
    var email:String = "",
    var eventsID:ArrayList<EventModel> = arrayListOf(),
    var eventsRef:String = "",
    var products:ArrayList<ProductModel> = arrayListOf(),
    var sales:ArrayList<TransactionModel> = arrayListOf(),
    var bannerCardURL:String? = null,
    var bannerBackgroundURL:String? = null,

)