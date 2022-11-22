package com.riseup.riseup_users.util

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ShowCardsDialogBinding
import com.riseup.riseup_users.view.ConfigUserPaymentsAddCardActivity
import com.riseup.riseup_users.view.FinishPaymentActivity
import com.riseup.riseup_users.view.fragments.PaymentCodeFragment

class PaymentDialog(
    private val onSubmitClickListener: (String) -> Unit
):DialogFragment() {

    //STATE
    private val adapter = UserAddedCardsAdapter()
    private lateinit var paymentCodeFragment: PaymentCodeFragment


    private lateinit var binding : ShowCardsDialogBinding

    override fun onStart() {
        super.onStart()
        getDialog()!!.window!!
            .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ShowCardsDialogBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val useraddedcardsRecycler = binding.rVAddedCards
        useraddedcardsRecycler.setHasFixedSize(true)
        useraddedcardsRecycler.layoutManager = LinearLayoutManager(activity)
        useraddedcardsRecycler.adapter = adapter
        useraddedcardsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val dialog = builder.create()

        binding.addNewCardMainBtnPU.setOnClickListener{

            val switchActivityIntent = Intent(requireContext(), ConfigUserPaymentsAddCardActivity::class.java)

            startActivity(switchActivityIntent)


        }
        binding.continuePaymentBtn.setOnClickListener {

            paymentCodeFragment = PaymentCodeFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentCodeFragment)
            transaction.commit()
            dismiss()

        }
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return dialog
    }
}