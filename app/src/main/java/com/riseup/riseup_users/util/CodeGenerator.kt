package com.riseup.riseup_users.util

import java.util.*

class CodeGenerator() {

    fun generateCode() : String{
        return "${randomLetter()}${randomNum()}${randomLetter()}${randomNum()}${randomLetter()}"
    }

    private fun randomNum() : Int{
        return Random().nextInt(10)
    }

    private fun randomLetter() : Char{
        return (Random().nextInt(26) + 65).toChar()
    }
}