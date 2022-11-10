package com.riseup.riseup_users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel(){

    private val discoArray = arrayListOf<DiscoModel>()
    private val _discos: MutableLiveData<ArrayList<DiscoModel>> = MutableLiveData(arrayListOf())
    val discos: LiveData<ArrayList<DiscoModel>> get() = _discos

    fun subscribeRealTimeDiscos() {
        viewModelScope.launch(Dispatchers.IO){
            Firebase.firestore.collection("Discos").addSnapshotListener{data, e->
                for (doc in data!!.documentChanges){
                    if(doc.type.name == "ADDED"){
                        val thisDisco = doc.document.toObject(DiscoModel::class.java)
                        Log.e(">>>", "ADEED: $thisDisco")
                        discoArray.add(thisDisco)
                        _discos.value = discoArray
                    } else if (doc.type.name == "MODIFIED"){
                        val thisDisco = doc.document.toObject(DiscoModel::class.java)
                        Log.e(">>>", "MODIFIED: $thisDisco")
                        for (disco in discoArray){
                            if(disco.id == thisDisco.id){
                                val index = discoArray.indexOf(disco)
                                discoArray[index] = thisDisco
                                _discos.value = discoArray
                                //Log.e(">>>", "Cambio en array${discoArray[index].state}")
                                break
                            }
                        }
                    }
                }
            }
        }
    }

    fun isNullUser(user: UserModel?) : Boolean{
        var state = true
        if(user == null || Firebase.auth.currentUser == null || Firebase.auth.currentUser?.isEmailVerified == false){
            //No hace nada
        } else {
            state = false
        }
        return state
    }
}