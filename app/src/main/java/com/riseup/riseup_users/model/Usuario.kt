package com.riseup.riseup_users.model

import java.util.*
import kotlin.collections.ArrayList

class Usuario (
    var celular:String ="",
    var correo:String = "",
    var diamantes:Int = 0,
    var edad:Int = 0,
    var favoritas:ArrayList<String> = arrayListOf(),
    val id:String = "",
    var nacimiento:Date,
    var nacionalidad:String ="",
    var nombre:String = "",
    var sexo:String =""

)