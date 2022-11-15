package com.riseup.riseup_users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FinishPaymentViewModel : ViewModel() {

    private val _updatedUser: MutableLiveData<UserModel> = MutableLiveData()
    val updatedUser: LiveData<UserModel> get() = _updatedUser

    fun saveTransaction(transaction : TransactionModel, user: UserModel){
        Firebase.firestore.collection("Sales").document(transaction.id).set(transaction)
        val updateMap: MutableMap<String, Any> = HashMap()
        updateMap["id"] = transaction.id
        FirebaseFirestore.getInstance().collection("Users")
            .document(user.id).collection("Purchase").document(transaction.id).update(updateMap)
    }

    suspend fun updateUser(user : UserModel, newDiamonds : Double) : Boolean = suspendCoroutine{ cont ->
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Users").document(user.id).update("diamonds",newDiamonds).addOnSuccessListener {
                user.diamonds = newDiamonds
                _updatedUser.postValue(user)
                cont.resume(true)
            }.addOnFailureListener {
                cont.resume(false)
            }.await()
        }
    }

}
