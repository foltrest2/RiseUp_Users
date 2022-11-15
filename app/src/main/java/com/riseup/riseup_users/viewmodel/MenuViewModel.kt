package com.riseup.riseup_users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductModel
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MenuViewModel : ViewModel() {

    private val discoArray = arrayListOf<DiscoModel>()
    private val imageArray = arrayListOf<String>()
    private val hashMap: HashMap<DiscoModel, List<ProductModel>> = hashMapOf()

    private val _discos: MutableLiveData<ArrayList<DiscoModel>> = MutableLiveData(arrayListOf())
    val discos: LiveData<ArrayList<DiscoModel>> get() = _discos

    private val _inComingImg: MutableLiveData<ArrayList<String>> = MutableLiveData(arrayListOf())
    val inComingImg: LiveData<ArrayList<String>> get() = _inComingImg

    fun loadDiscos() {
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Discos").addSnapshotListener { data, e ->
                for (doc in data!!.documentChanges) {
                    if (doc.type.name == "ADDED") {
                        val thisDisco = doc.document.toObject(DiscoModel::class.java)
                        Log.e(">>>", "ADEED: $thisDisco")
                        discoArray.add(thisDisco)
                        _discos.value = discoArray
                        Firebase.storage.getReference(thisDisco.bannerRef)
                            .child(thisDisco.bannerCardID).downloadUrl.addOnSuccessListener {
                                imageArray.add(it.toString())
                                _inComingImg.value = imageArray
                            }
                    } else if (doc.type.name == "MODIFIED") {
                        val thisDisco = doc.document.toObject(DiscoModel::class.java)
                        Log.e(">>>", "MODIFIED: $thisDisco")
                        for (disco in discoArray) {
                            if (disco.id == thisDisco.id) {
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

    fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Discos").get().addOnSuccessListener { discos ->
                for (disco in discos) {
                    Firebase.firestore.collection("Discos").document(disco.id)
                        .collection("Products").get().addOnSuccessListener { products ->
                            val thisProducts = products.toObjects(ProductModel::class.java)
                            val thisDisco = disco.toObject(DiscoModel::class.java)
                            hashMap[thisDisco] = thisProducts
                            Log.e(">>>", "Products stored: ${hashMap[thisDisco]}")
                        }
                }
            }
        }
    }

    fun getProducts(discoModel: DiscoModel) : List<ProductModel>? {
        Log.e(">>>", "Products: ${hashMap[discoModel]}")
        return hashMap[discoModel]
    }

    fun saveTransaction(transaction : TransactionModel, user: UserModel){
        Firebase.firestore.collection("Sales").document(transaction.id).set(transaction)
        val updateMap: MutableMap<String, Any> = HashMap()
        updateMap["id"] = transaction.id
        FirebaseFirestore.getInstance().collection("Users")
            .document(user.id).collection("Purchase").document(transaction.id).update(updateMap)
    }

    fun isNullUser(user: UserModel?): Boolean {
        var state = true
        if (user == null || Firebase.auth.currentUser == null || Firebase.auth.currentUser?.isEmailVerified == false) {
            //No hace nada
        } else {
            state = false
        }
        return state
    }
}