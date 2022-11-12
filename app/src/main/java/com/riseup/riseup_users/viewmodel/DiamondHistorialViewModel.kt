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
import com.riseup.riseup_users.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DiamondHistorialViewModel : ViewModel(){

    private val transactionsArray = arrayListOf<TransactionModel>()
    private val _transactions: MutableLiveData<ArrayList<TransactionModel>> = MutableLiveData(arrayListOf())
    val transactions: LiveData<ArrayList<TransactionModel>> get() = _transactions

    fun loadTransactions(user: User){
        viewModelScope.launch(Dispatchers.IO){
            Firebase.firestore.collection("Sales").whereEqualTo("userID",user.id).get().addOnSuccessListener {data ->
                for(doc in data.documents){
                    val thisTransaction = doc.toObject(TransactionModel::class.java)
                    Firebase.firestore.collection("Discos").document(thisTransaction!!.discoID).get().addOnSuccessListener {
                        val thisDisco = it.toObject(DiscoModel::class.java)
                        thisTransaction.discoName = thisDisco!!.name
                        transactionsArray.add(thisTransaction)
                        _transactions.postValue(transactionsArray)
                    }
                    }
                }
            }
        }


}