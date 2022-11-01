package com.riseup.riseup_users.util

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ErrorDialogBinding

class ErrorDialog:DialogFragment() {
    //STATE

    private lateinit var binding:ErrorDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ErrorDialogBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val bundle = arguments
        val desc = bundle!!.getString("TEXT", "")
        changeDialogDesc(desc)
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        binding.confirmPassNotMatchDialogBtn.setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }

    fun changeDialogDesc(desc:String){
        when(desc){
            "InvalidEmail"-> binding.passNotMatchdescTV.text = getString(R.string.ErrorDialogInvalidEmailLoginTV)
            "WrongPassword"-> binding.passNotMatchdescTV.text = getString(R.string.ErrorDialogWrongPasswordLoginTV)
            "UserNotFound"-> binding.passNotMatchdescTV.text = getString(R.string.ErrorDialogUserNotFoundTV)
            "WeakPass"-> binding.passNotMatchdescTV.text = getString(R.string.ErrorDialogWeakPassTV)
            "PasswordNotMatch"-> binding.passNotMatchdescTV.text = getString(R.string.ErrorDialogPassNotMatchTV)
            "EmptyFields"-> binding.passNotMatchdescTV.text = getString(R.string.ErrorDialogEmptyFieldsTV)

        }


    }

}