package com.riseup.riseup_users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riseup.riseup_users.model.User

class ProductsListViewModel : ViewModel(){

    private val _inComingUser = MutableLiveData<User>()
    val inComingUser : LiveData<User> get() = _inComingUser

    fun setSpUser(user: User){
        _inComingUser.value = user
        Log.e(">>>","IncomingUser Seted: ${_inComingUser.value}")
    }



}