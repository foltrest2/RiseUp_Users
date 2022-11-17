package com.riseup.riseup_users.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.view.CardsLastPaymentsView
import java.text.SimpleDateFormat

class UserCardsPaymentAdapter : RecyclerView.Adapter<CardsLastPaymentsView>(){

    private val paymentsCards = ArrayList<TransactionModel>()

    //Genera un esqueleto gracias al XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsLastPaymentsView {
        //XML a View
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.cards_user_payments_row, parent, false)
        val cardsView = CardsLastPaymentsView(row)
        return cardsView
    }

    //Con el esqueleto ya formado, se le ponen los datos correspondientes al esqueleto
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(skeleton: CardsLastPaymentsView, position: Int) {
        val payments = paymentsCards[position]
        skeleton.discoName.text = payments.discoName
        skeleton.cardInfo.text = payments.method

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.format(payments.date!!).toString()
        skeleton.payDate.setText(date)
        skeleton.amount.text = payments.totalPay.toString()

    }

    override fun getItemCount(): Int {
        return paymentsCards.size
    }

   fun addPay(pay : TransactionModel){
        paymentsCards.add(pay)
        notifyItemInserted(paymentsCards.lastIndex)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun reset(){
        paymentsCards.clear()
        notifyDataSetChanged()
    }
}