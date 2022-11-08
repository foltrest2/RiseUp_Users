package com.riseup.riseup_users.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.User

class MenuViewModel : ViewModel(){

    fun isNullUser(user: User?) : Boolean{
        var state = true
        if(user == null || Firebase.auth.currentUser == null || Firebase.auth.currentUser?.isEmailVerified == false){
            //No hace nada
        } else {
            state = false
        }
        return state
    }
}