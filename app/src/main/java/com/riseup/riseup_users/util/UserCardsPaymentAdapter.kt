package com.riseup.riseup_users.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.CardLastPaymentsModel
import com.riseup.riseup_users.view.CardsLastPaymentsView

class UserCardsPaymentAdapter : RecyclerView.Adapter<CardsLastPaymentsView>(){

    private val paymentsCards = ArrayList<CardLastPaymentsModel>()

    init {

        paymentsCards.add(CardLastPaymentsModel("Espacio 10-60","MASTERCARD****3241",50000.0,"10/11/22"))
        paymentsCards.add(CardLastPaymentsModel("La Over","MASTERCARD****3241",40000.0,"3/11/22"))
        paymentsCards.add(CardLastPaymentsModel("Cardinals","VISA****8024",80000.0,"28/10/22"))
        paymentsCards.add(CardLastPaymentsModel("La Pérgola","VISA****3241",60800.0,"15/10/22"))
        paymentsCards.add(CardLastPaymentsModel("La Premiere","VISA****3241",90000.0,"8/10/22"))
        paymentsCards.add(CardLastPaymentsModel("La Over","MASTERCARD****5287",102500.0,"1/10/22"))
        paymentsCards.add(CardLastPaymentsModel("Espacio 10-60","MASTERCARD****5035",85450.0,"24/9/22"))
        paymentsCards.add(CardLastPaymentsModel("Espacio 10-60","MASTERCARD****5035",50500.0,"17/9/22"))
        paymentsCards.add(CardLastPaymentsModel("Espacio 10-60","MASTERCARD****5035",50000.0,"10/9/22"))


    }

    //Genera un esqueleto gracias al XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsLastPaymentsView {
        //XML a View
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.cards_user_payments_row, parent, false)
        val cardsView = CardsLastPaymentsView(row)
        return cardsView
    }

    //Con el esqueleto ya formado, se le ponen los datos correspondientes al esqueleto
    override fun onBindViewHolder(skeleton: CardsLastPaymentsView, position: Int) {
        val payments = paymentsCards[position]
        skeleton.discoName.text = payments.discoName
        skeleton.cardInfo.text = payments.cardInfo
        skeleton.amount.text = payments.amount.toString()
        skeleton.payDate.text = payments.date
    }

    override fun getItemCount(): Int {
        return paymentsCards.size
    }
}