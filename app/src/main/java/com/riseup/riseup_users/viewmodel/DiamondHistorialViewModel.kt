package com.riseup.riseup_users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DiamondHistorialViewModel : ViewModel(){

    private val transactionsArray = arrayListOf<TransactionModel>()
    private val _transactions: MutableLiveData<ArrayList<TransactionModel>> = MutableLiveData(arrayListOf())
    val transactions: LiveData<ArrayList<TransactionModel>> get() = _transactions

    fun loadTransactions(user: UserModel){
        viewModelScope.launch(Dispatchers.IO){
            val transactions = Firebase.firestore.collection("Sales").whereEqualTo("userID",user.id).get().await()
            withContext(Dispatchers.Main){
                for(doc in transactions.documents){
                    val thisTransaction = doc.toObject(TransactionModel::class.java)
                    transactionsArray.add(thisTransaction!!)
                    _transactions.postValue(transactionsArray)
                }
            }
            }
        }
}