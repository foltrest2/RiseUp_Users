package com.riseup.riseup_users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riseup.riseup_users.model.UserModel

class ProductsListViewModel : ViewModel(){

    private val _inComingUser = MutableLiveData<UserModel>()
    val inComingUser : LiveData<UserModel> get() = _inComingUser

    fun setSpUser(user: UserModel){
        _inComingUser.value = user
        Log.e(">>>","IncomingUser Seted: ${_inComingUser.value}")
    }



}