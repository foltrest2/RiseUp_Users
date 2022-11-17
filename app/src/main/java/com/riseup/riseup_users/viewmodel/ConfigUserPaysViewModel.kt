package com.riseup.riseup_users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ConfigUserPaysViewModel : ViewModel(){

    private val paymentsArray = arrayListOf<TransactionModel>()
    private val _payments:MutableLiveData<ArrayList<TransactionModel>> = MutableLiveData(arrayListOf())
    val payments:LiveData<ArrayList<TransactionModel>> get() = _payments

    fun loadPayments(user : UserModel){
        viewModelScope.launch(Dispatchers.IO) {
            val payments = Firebase.firestore.collection("Sales").whereEqualTo("userID", user.id).get().await()
                for(doc in payments.documents){
                    val thisPay = doc.toObject(TransactionModel::class.java)
                    withContext(Dispatchers.Main){
                    paymentsArray.add(thisPay!!)
                    _payments.value = paymentsArray
                }
            }

        }
    }


}