package com.riseup.riseup_users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

import kotlinx.coroutines.withContext


class LoginViewModel: ViewModel() {

    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting...")
    )
    val authState : LiveData<AuthState> get() = _authState

    //Accion de registro
    fun signIn(correo:String, pass:String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val result = Firebase.auth.signInWithEmailAndPassword(correo,pass).addOnCompleteListener { task->
                    if (!task.isSuccessful) {
                        try {
                            throw task.exception!!
                        }
                        catch (authException: FirebaseAuthException ) {

                           when(authException.errorCode){

                               "ERROR_WRONG_PASSWORD" -> _authState.value = AuthState(AuthResult.FAIL, "wrongPassword")
                               "ERROR_INVALID_EMAIL" -> _authState.value = AuthState(AuthResult.FAIL, "invalidEmail")
                               "ERROR_USER_NOT_FOUND" -> _authState.value = AuthState(AuthResult.FAIL, "userNotFound")

                           }
                        }
                    }else{
                    }
                }.await()

                withContext(Dispatchers.Main){ _authState.value = AuthState(AuthResult.SUCCESS, "Success") }

            }catch (ex: FirebaseAuthException){
                Log.e(">>>", ex.errorCode)
                withContext(Dispatchers.Main){ _authState.value = AuthState(AuthResult.FAIL, ex.localizedMessage)}
            }

        }
    }

}

