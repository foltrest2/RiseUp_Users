package com.riseup.riseup_users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ConfigProfileChangeTelViewModel : ViewModel() {

    private val _inComingUser = MutableLiveData<UserModel>()
    val inComingUser : LiveData<UserModel> get() = _inComingUser

    fun setSpUser(user: UserModel){
        _inComingUser.value = user
        Log.e(">>>","IncomingUser Seted: ${_inComingUser.value}")
    }

    suspend fun changeTel(user : UserModel, newCel : String) : Boolean = suspendCoroutine{ cont ->
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Users").document(user.id).update("cel",newCel).addOnSuccessListener {
                user.cel = newCel
                _inComingUser.postValue(user)
                cont.resume(true)
            }.addOnFailureListener {
                cont.resume(false)
            }.await()
        }
    }

}