package com.riseup.riseup_users.model

data class Discoteca (
    val id:String = "",
    var nombre:String = "",
    var bannerID:String = "",
    var bannerRef:String = "",
    var email:String = "",
    var eventosID:ArrayList<Evento> = ArrayList(),
    var productos:ArrayList<Evento> = ArrayList(),
    var ventas:ArrayList<Evento> = ArrayList()
)
