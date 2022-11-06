package com.riseup.riseup_users.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ConfigProfileChangeTelViewModel : ViewModel() {


    fun changeTel(user : Usuario, newCel : String){
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Usuarios").document(user.id).update("celular",newCel).addOnSuccessListener {
            }.await()
        }
    }



}