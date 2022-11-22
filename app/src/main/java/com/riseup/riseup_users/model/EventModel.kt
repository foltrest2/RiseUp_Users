package com.riseup.riseup_users.model

import java.util.*

data class EventModel (
    val id:String = "",
    var date:Date? = null,
    var posterID:String = "",
    var posterURL:String? = null
)
