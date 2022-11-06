package com.riseup.riseup_users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ConfigProfileChangeSexViewModel : ViewModel() {
    fun updateSex(newSex : String, user : Usuario){
        viewModelScope.launch(Dispatchers.IO){
            Firebase.firestore.collection("Usuarios").document(user.id).update("sexo",newSex).addOnSuccessListener {

            }.await()
        }

    }

}