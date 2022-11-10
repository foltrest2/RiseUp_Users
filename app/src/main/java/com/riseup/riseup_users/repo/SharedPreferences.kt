package com.riseup.riseup_users.repo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.riseup.riseup_users.model.User


class SharedPreferences(context: Context) {

    val context: Context = context


    fun loadUser(): User? {
        val sp = context.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if (json == "NO_USER") {
            return null
        } else {
            return Gson().fromJson(json, User::class.java)
        }
    }

    fun saveUserSp(user: User) {
        val sp = context.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario", json).apply()
    }



}