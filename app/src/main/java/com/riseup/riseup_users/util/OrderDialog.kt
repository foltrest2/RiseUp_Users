package com.riseup.riseup_users.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_users.databinding.ShowOrderDialogBinding
import com.riseup.riseup_users.model.ProductsShoppingCarModel

class OrderDialog:DialogFragment() {
    //STATE
    private val adapter = FinalOrderListAdapter()

    private lateinit var binding :ShowOrderDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ShowOrderDialogBinding.inflate(LayoutInflater.from(context))


        adapter.addProducts(loadShoppingCar()!!)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val orderlist = binding.rVPopUpOrderDetails
        orderlist.setHasFixedSize(true)
        orderlist.layoutManager = LinearLayoutManager(activity)
        orderlist.adapter = adapter
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }

    private fun loadShoppingCar(): ArrayList<ProductsShoppingCarModel>? {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("shoppingCar", "NO_CAR")
        return if (json == "NO_CAR") {
            null
        } else {
            val deserialized = object : TypeToken<ArrayList<ProductsShoppingCarModel>>() {}.type
            Gson().fromJson(json, deserialized)
        }
    }
}