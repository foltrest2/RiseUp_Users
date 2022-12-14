package com.riseup.riseup_users.model

import java.util.*
import kotlin.collections.ArrayList

data class UserModel (
    var cel:String ="",
    var email:String = "",
    var diamonds:Double = 0.0,
    var age:Int = 0,
    var favorites:ArrayList<String>? = null,
    val id:String = "",
    var birth:Date? = null,
    var nationality:String ="",
    var name:String = "",
    var sex:String ="",
    var profileImg:String = "",
)