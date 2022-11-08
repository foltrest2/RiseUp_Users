package com.riseup.riseup_users.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_users.databinding.ActivityConfigProfileInfoBinding
import com.riseup.riseup_users.model.User
import kotlinx.android.synthetic.main.activity_config_profile_info.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class ConfigProfileInfoViewModel : ViewModel(){

    private val _inComingUser = MutableLiveData<User>()
    val inComingUser : LiveData<User> get() = _inComingUser

    private lateinit var binding : ActivityConfigProfileInfoBinding

    fun setSpUser(user: User){
        _inComingUser.value = user
        Log.e(">>>","IncomingUser Seted: ${_inComingUser.value}")
    }

    @SuppressLint("SimpleDateFormat", "NewApi")
    fun changeDate(user : User, newDate : Date){
        //_inComingUser.value = user
        val newAge = calculateAge(newDate)
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Users").document(user.id)
                .update("birth",newDate,"age",newAge).addOnSuccessListener {
                        user.birth = newDate
                        _inComingUser.postValue(user)
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

    fun uploadProfileImg(user: User, uriImage: Uri) {
        val filename = UUID.randomUUID().toString()
        val oldImg = user.profileImg
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.storage.getReference("/Usuarios perfiles").child(filename).putFile(uriImage).addOnSuccessListener {
                Firebase.firestore.collection("Users").document(user.id).update("profileImg",filename).addOnSuccessListener {
                    user.profileImg = filename
                    _inComingUser.postValue(user)
                    if(oldImg.isNotEmpty()){
                        Firebase.storage.getReference("/Usuarios perfiles").child(oldImg).delete()
                    }
                }
            }.await()
        }
    }

    fun updateImage(user: User) {
        viewModelScope.launch(Dispatchers.IO){
            Firebase.firestore.collection("Users").document(user.id).get().addOnSuccessListener {
                val updatedUser = it.toObject(User::class.java)
                val profileID = updatedUser?.profileImg
                downloadProfileImage(profileID)
            }.await()
        }
    }

    private fun downloadProfileImage(profileImg : String?) {
        if(!profileImg.isNullOrEmpty()){
            viewModelScope.launch(Dispatchers.IO) {
                Firebase.storage.getReference("/Usuarios perfiles")
                    .child(profileImg).downloadUrl.addOnSuccessListener {
                        Glide.with(binding.profileInfoPImg).load(it)
                            .thumbnail(.5f)
                            .into(binding.profileInfoPImg)
                }.await()
            }
        }
    }

    fun setBinding(binding: ActivityConfigProfileInfoBinding) {
        this.binding = binding
    }

}