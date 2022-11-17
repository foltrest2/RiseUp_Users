package com.riseup.riseup_users.viewmodel

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_users.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*


class RegisterViewModel: ViewModel() {

    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting...")
    )
    val authState : LiveData<AuthState> get() = _authState

    //Accion de registro
    fun signUp(celular:String, correo:String, diamantes:Double, nacimiento:String, nacionalidad:String,nombre:String,sexo:String,pass:String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val result = Firebase.auth.createUserWithEmailAndPassword(
                    correo,
                    pass).addOnCompleteListener { task->
                    if (!task.isSuccessful) {
                        try {
                            throw task.exception!!
                        }
                        catch (weakPassword: FirebaseAuthWeakPasswordException) {
                            _authState.value = AuthState(AuthResult.FAIL, "WeakPass")
                        }
                        catch (malformedEmail: FirebaseAuthInvalidCredentialsException) {
                            _authState.value = AuthState(AuthResult.FAIL, "RepeatedEmail")

                        } catch (existEmail: FirebaseAuthUserCollisionException) {

                          _authState.value = AuthState(AuthResult.FAIL, "RepeatedEmail")

                        } catch (e: Exception) {

                        }
                    }else{
                        Firebase.auth.currentUser!!.sendEmailVerification()
                    }
                }
                    .await()
                Log.e(">>>", Firebase.auth.currentUser!!.uid)
                //Registrar el objeto en Firestore
                val df: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
                df.timeZone = TimeZone.getTimeZone("America/Bogota")
                val date = df.parse(nacimiento)
                val edad = calculateAge(date)

                val user = UserModel(celular,correo, diamantes, edad, arrayListOf(),Firebase.auth.currentUser!!.uid,date, nacionalidad, nombre, sexo)
                Firebase.firestore.collection("Users")
                    .document(user.id).set(user).await()

                withContext(Dispatchers.Main){ _authState.value = AuthState(AuthResult.SUCCESS, "Success") }

            }catch (ex: Exception){
                Log.e(">>>", ex.localizedMessage)
                withContext(Dispatchers.Main){ _authState.value = AuthState(AuthResult.FAIL, ex.localizedMessage)}
            }

        }
    }

    fun calculateAge(date:Date):Int{

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

    fun validatePassword(pass:String, confpass: String):Boolean{
        var eqpass = true
        if(pass != confpass){
            eqpass = false
        }else{

        }
        return eqpass
    }
}
data class AuthState(val result:AuthResult, val message:String)
enum class AuthResult{ IDLE, FAIL, SUCCESS }