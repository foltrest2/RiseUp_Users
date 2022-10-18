package com.riseup.riseup_users.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.ShowOrderDialogBinding

class OrderDialog:DialogFragment() {
    //STATE
    private val adapter = ProductsShoppingCarAdapter()
    private lateinit var binding :ShowOrderDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ShowOrderDialogBinding.inflate(LayoutInflater.from(context))

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
}