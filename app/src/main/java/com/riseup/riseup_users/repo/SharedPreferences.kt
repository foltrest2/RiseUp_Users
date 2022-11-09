package com.riseup.riseup_users.repo

import android.content.Context


class SharedPreferences(context: Context) {

    val context: Context = context


    init {
        context.getSharedPreferences()
    }

}