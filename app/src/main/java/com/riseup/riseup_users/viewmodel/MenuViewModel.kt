package com.riseup.riseup_users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MenuViewModel : ViewModel() {

    private val discoArray = arrayListOf<DiscoModel>()
    private val imageArray = arrayListOf<String>()
    private val hashMap: HashMap<String, List<ProductModel>> = hashMapOf()

    private val _discos: MutableLiveData<ArrayList<DiscoModel>> = MutableLiveData(arrayListOf())
    val discos: LiveData<ArrayList<DiscoModel>> get() = _discos

    private val _inComingImg: MutableLiveData<ArrayList<String>> = MutableLiveData(arrayListOf())
    val inComingImg: LiveData<ArrayList<String>> get() = _inComingImg

    fun saveTransaction(transaction : TransactionModel, user: UserModel){
        var totalPay : Int = 0
        for (shoppingCar in transaction.shoppingCar!!){
            totalPay += shoppingCar.lot * shoppingCar.price
        }
        transaction.totalPay = totalPay

        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Sales").document(transaction.id).set(transaction)
            val updateMap: MutableMap<String, Any> = HashMap()
            updateMap["id"] = transaction.id
            val thisID = TransID(transaction.id)
            Firebase.firestore.collection("Users")
                .document(user.id).collection("Purchase").document(thisID.id).set(thisID)
        }
    }

    fun loadDiscos() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = Firebase.firestore.collection("Discos").get().await()
            for(doc in result) {
                val thisDisco = doc.toObject(DiscoModel::class.java)
                val bannercardURL = Firebase.storage.getReference(thisDisco.bannerRef).child(thisDisco.bannerCardID).downloadUrl.await().toString()
                thisDisco.bannerCardURL = bannercardURL
                val bannerURL = Firebase.storage.getReference(thisDisco.bannerRef).child(thisDisco.bannerID).downloadUrl.await().toString()
                thisDisco.bannerBackgroundURL = bannerURL
                for (event in thisDisco.eventsID){
                    val posterURL = Firebase.storage.getReference(thisDisco.eventsRef).child(event.posterID).downloadUrl.await().toString()
                    event.posterURL = posterURL
                }
                withContext(Dispatchers.Main){
                    Log.e(">>>", "Agregada: ${thisDisco}")
                    discoArray.add(thisDisco)
                }
            }
            withContext(Dispatchers.Main){
                _discos.value = discoArray
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
                            for (product in thisProducts){
                                viewModelScope.launch(Dispatchers.IO) {
                                    val productImgURL = Firebase.storage.getReference(thisDisco.productsRef).child(product.image).downloadUrl.await().toString()
                                    product.imageURL = productImgURL
                                }
                            }
                            hashMap[thisDisco.id] = thisProducts
                            Log.e(">>>", "Products stored: ${hashMap[thisDisco.id]}")
                        }
                }
            }
        }
    }

    fun getProducts(discoModel: DiscoModel) : List<ProductModel>? {
        Log.e(">>>", "Products: ${hashMap[discoModel.id]}")
        return hashMap[discoModel.id]
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
data class TransID(val id:String)