package com.riseup.riseup_users.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class ConfigProfileInfoViewModel : ViewModel(){


    @SuppressLint("SimpleDateFormat", "NewApi")
    fun changeDate(user : Usuario, newDate : Date){
        val newAge = calculateAge(newDate)
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Usuarios").document(user.id)
                .update("nacimiento",newDate,"edad",newAge).addOnSuccessListener {

                }.await()
        }
    }
    fun calculateAge(date: Date):Int{

        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()

        dob.time = date

        val year = dob[Calendar.YEAR]
        val month = dob[Calendar.MONTH]
        val day = dob[Calendar.DAY_OF_MONTH]

        dob[year, month + 1] = day

        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]

        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
            age--
        }
        return age
    }

}